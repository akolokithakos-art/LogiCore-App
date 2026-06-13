package com.example.logicore.features.orchestration.model

sealed class OrchestrationResult {

    data class Success(val message: String) : OrchestrationResult()

    data class Rejected(val reason: String) : OrchestrationResult()
}