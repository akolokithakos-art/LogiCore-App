package com.example.logicore.features.core.resilience

class CircuitBreaker(
    private val threshold: Int = 5
) {

    private var failures = 0

    private var open = false

    fun allow(): Boolean {
        return !open
    }

    fun recordSuccess() {
        failures = 0
        open = false
    }

    fun recordFailure() {

        failures++

        if (failures >= threshold) {
            open = true
        }
    }

    fun isOpen(): Boolean {
        return open
    }

    fun reset() {
        failures = 0
        open = false
    }
}