package com.example.logicore.features.intelligence.forecast

data class RepositionPlan(
    val vehicleId: Int,
    val targetZoneId: Int,
    val priority: Double
)