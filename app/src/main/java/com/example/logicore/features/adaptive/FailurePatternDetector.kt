package com.example.logicore.features.events.adaptive

class FailurePatternDetector {

    private val recentFailures = mutableMapOf<String, Int>()

    fun isPoisonEvent(projection: String): Boolean {

        val count = recentFailures.getOrDefault(projection, 0)

        return count > 10
    }

    fun recordFailure(projection: String) {

        recentFailures[projection] =
            recentFailures.getOrDefault(projection, 0) + 1
    }
}