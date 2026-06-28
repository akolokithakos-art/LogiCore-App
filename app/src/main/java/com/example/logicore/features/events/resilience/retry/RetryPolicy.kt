package com.example.logicore.features.events.resilience.retry

data class RetryPolicy(
    val maxRetries: Int = 5,
    val baseDelayMs: Long = 500,
    val multiplier: Double = 2.0
) {

    fun nextDelay(attempt: Int): Long {
        return (baseDelayMs * Math.pow(multiplier, attempt.toDouble())).toLong()
    }
}