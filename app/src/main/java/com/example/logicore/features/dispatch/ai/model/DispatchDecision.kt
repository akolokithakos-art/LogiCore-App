package com.example.logicore.features.dispatch.ai.model

data class DispatchDecision(
    val vehicleId: Int,
    val score: Double,
    val reason: String
)