package com.example.logicore.features.dispatch.execution

sealed class ExecutionResult {

    data class Success(val message: String) : ExecutionResult()

    data class Failed(val reason: String) : ExecutionResult()
}