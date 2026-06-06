package com.example.logicore.features.dispatch.domain.model

data class DispatchPlan(
    val vehicleId: Int,
    val productId: Int,
    val zoneId: Int?,
    val recommendedLoad: Double,
    val priority: String // LOW / MEDIUM / HIGH
)