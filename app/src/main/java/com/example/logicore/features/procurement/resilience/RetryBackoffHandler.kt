package com.example.logicore.features.procurement.resilience

import com.example.logicore.features.events.resilience.ResilientProjectionExecutor
import com.example.logicore.features.events.resilience.DeadLetterQueue
import kotlinx.coroutines.delay

class RetryBackoffHandler(
    private val dlq: DeadLetterQueue
) {

    suspend fun executeWithRetry(
        block: suspend () -> Unit,
        maxRetries: Int = 3
    ) {

        var attempt = 0

        while (attempt < maxRetries) {

            try {
                block()
                return
            } catch (e: Exception) {

                attempt++

                if (attempt >= maxRetries) {
                    dlq.push(
                        event = null!!,
                        error = e.message ?: "unknown",
                        projection = "PROCUREMENT"
                    )
                    return
                }

                delay((1000L * attempt)) // exponential backoff
            }
        }
    }


}
