package com.example.logicore.features.intelligence.core

data class VehicleIntelSnapshot(
    val vehicleId: Int,
    val stockPressure: Double,
    val demandPressure: Double,
    val utilizationScore: Double,
    val riskLevel: String
)