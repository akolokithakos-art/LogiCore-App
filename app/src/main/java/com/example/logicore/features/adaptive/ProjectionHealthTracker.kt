package com.example.logicore.features.events.adaptive

import java.util.concurrent.ConcurrentHashMap

class ProjectionHealthTracker {

    private val metrics = ConcurrentHashMap<String, ProjectionHealth>()

    fun recordFailure(projection: String) {
        update(projection, failure = true)
    }

    fun recordSuccess(projection: String) {
        update(projection, failure = false)
    }

    private fun update(projection: String, failure: Boolean) {

        val current = metrics[projection] ?: ProjectionHealth(
            projection = projection,
            failureRate = 0.0,
            avgRetryCount = 0.0,
            lastUpdated = System.currentTimeMillis(),
            healthScore = 1.0
        )

        val newFailureRate =
            if (failure) (current.failureRate + 0.05).coerceAtMost(1.0)
            else (current.failureRate - 0.02).coerceAtLeast(0.0)

        val score = 1.0 - newFailureRate

        metrics[projection] = current.copy(
            failureRate = newFailureRate,
            healthScore = score,
            lastUpdated = System.currentTimeMillis()
        )
    }

    fun getHealth(projection: String): ProjectionHealth? {
        return metrics[projection]
    }
}