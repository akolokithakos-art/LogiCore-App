package com.example.logicore.features.events.resilience

import com.example.logicore.features.events.projections.EventProjection
import com.example.logicore.features.events.data.local.EventStoreEntity
import com.example.logicore.features.events.resilience.retry.RetryPolicy
import com.example.logicore.features.events.resilience.retry.RetryScheduler
import com.example.logicore.features.events.resilience.retry.RetryState

class ResilientProjectionExecutor(
    private val policy: RetryPolicy,
    private val retryScheduler: RetryScheduler,
    private val dlq: DeadLetterQueue
) {

    suspend fun execute(
        projection: EventProjection,
        event: EventStoreEntity
    ): ProjectionResult {

        return try {

            if (!projection.supports(event)) {
                return ProjectionResult.Success
            }

            projection.handle(event)

            ProjectionResult.Success

        } catch (e: Exception) {

            handleFailure(
                event,
                projection::class.simpleName ?: "unknown",
                e
            )

            ProjectionResult.Failed(e)
        }
    }

    fun shouldRetry(state: RetryState): Boolean {
        return state.attempt < policy.maxRetries
    }

    private suspend fun handleFailure(
        event: EventStoreEntity,
        projection: String,
        error: Throwable
    ) {

        val state = RetryState(
            eventId = event.id.toString(),
            projection = projection,
            attempt = 1,
            lastError = error.message
        )

        if (shouldRetry(state)) {

            retryScheduler.schedule(state)

        } else {

            dlq.push(
                event,
                error.message ?: "unknown",
                projection
            )
        }
    }
}