package com.example.logicore.features.events.resilience.worker

import com.example.logicore.features.events.resilience.retry.RetryQueueDao
import com.example.logicore.features.events.resilience.retry.RetryPolicy
import com.example.logicore.features.events.resilience.retry.RetryQueueEntity
import com.example.logicore.features.events.resilience.ResilientProjectionExecutor
import com.google.gson.Gson
import kotlinx.coroutines.*

class SelfHealingWorker(
    private val dao: RetryQueueDao,
    private val executor: ResilientProjectionExecutor,
    private val retryPolicy: RetryPolicy,
    private val gson: Gson,
    private val scope: CoroutineScope
) {

    fun start() {

        scope.launch(Dispatchers.IO) {

            while (true) {

                try {

                    val now = System.currentTimeMillis()

                    val batch = dao.fetchDue(
                        now = now,
                        limit = 50
                    )

                    if (batch.isNotEmpty()) {
                        process(batch)
                    }

                    delay(2_000) // polling interval

                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    private suspend fun process(
        items: List<RetryQueueEntity>
    ) {

        items.forEach { item ->

            val event = gson.fromJson(
                item.payload,
                com.example.logicore.features.events.data.local.EventStoreEntity::class.java
            )

            val result = executor.execute(
                projection = resolveProjection(item.projection),
                event = event
            )

            when (result) {

                is com.example.logicore.features.events.resilience.ProjectionResult.Success -> {
                    dao.delete(item.id)
                }

                is com.example.logicore.features.events.resilience.ProjectionResult.Failed -> {

                    scheduleRetry(item, result.error)
                }

                else -> {}
            }
        }
    }

    private fun scheduleRetry(
        item: RetryQueueEntity,
        error: Throwable
    ) {

        val nextAttempt = item.attempt + 1

        val delay = retryPolicy.nextDelay(nextAttempt)

        val updated = item.copy(
            attempt = nextAttempt,
            nextRetryAt = System.currentTimeMillis() + delay,
            lastError = error.message
        )

        scope.launch {
            dao.insert(updated)
        }
    }

    private fun resolveProjection(name: String): Nothing
            = TODO("map projection registry here")
}