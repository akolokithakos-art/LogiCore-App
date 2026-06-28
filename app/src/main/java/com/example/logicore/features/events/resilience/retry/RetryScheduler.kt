package com.example.logicore.features.events.resilience.retry

import kotlinx.coroutines.*
import java.util.concurrent.ConcurrentHashMap

class RetryScheduler(
    private val scope: CoroutineScope,
    private val policy: RetryPolicy,
    private val executor: suspend (RetryState) -> Unit
) {

    private val states = ConcurrentHashMap<String, RetryState>()

    fun schedule(state: RetryState) {

        val key = "${state.eventId}:${state.projection}"

        states[key] = state

        val delay = policy.nextDelay(state.attempt)

        scope.launch {

            delay(delay)

            executor(state)
        }
    }
}