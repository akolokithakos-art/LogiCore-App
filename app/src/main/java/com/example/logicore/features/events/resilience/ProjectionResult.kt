package com.example.logicore.features.events.resilience

sealed class ProjectionResult {

    object Success : ProjectionResult()

    data class Retry(
        val reason: String
    ) : ProjectionResult()

    data class Failed(
        val error: Throwable
    ) : ProjectionResult()
}