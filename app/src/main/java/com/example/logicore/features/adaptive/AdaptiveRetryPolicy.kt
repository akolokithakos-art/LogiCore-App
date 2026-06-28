package com.example.logicore.features.events.adaptive

class AdaptiveRetryPolicy(
    private val healthTracker: ProjectionHealthTracker
) {

    fun maxRetries(projection: String): Int {

        val health = healthTracker.getHealth(projection)

        return when {

            health == null -> 5

            health.healthScore > 0.8 -> 3   // healthy → fewer retries

            health.healthScore > 0.5 -> 5

            else -> 8   // unstable → more retries
        }
    }

    fun backoffMultiplier(projection: String): Double {

        val health = healthTracker.getHealth(projection)

        return when {

            health == null -> 2.0

            health.healthScore > 0.8 -> 1.5

            health.healthScore > 0.5 -> 2.0

            else -> 3.0 // unstable system → slower retry spam
        }
    }
}