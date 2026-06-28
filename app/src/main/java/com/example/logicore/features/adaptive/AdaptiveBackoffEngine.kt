package com.example.logicore.features.events.adaptive

class AdaptiveBackoffEngine(
    private val policy: AdaptiveRetryPolicy
) {

    fun nextDelay(
        projection: String,
        attempt: Int
    ): Long {

        val multiplier = policy.backoffMultiplier(projection)

        return (500 * Math.pow(multiplier, attempt.toDouble())).toLong()
    }
}