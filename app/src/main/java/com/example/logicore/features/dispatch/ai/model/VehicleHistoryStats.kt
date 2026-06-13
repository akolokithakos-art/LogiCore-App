package com.example.logicore.features.dispatch.ai.model

data class VehicleHistoryStats(
    val vehicleId: Int,
    val successRate: Double,
    val avgDeliveryTimeScore: Double,
    val rejectionRate: Double
)