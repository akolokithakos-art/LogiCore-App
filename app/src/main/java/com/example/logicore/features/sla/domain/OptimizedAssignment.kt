package com.example.logicore.features.sla.domain

data class OptimizedAssignment(

    val driverId: String,
    val vehicleId: String,

    val totalScore: Double,
    val estimatedEta: Double,
    val slaRisk: Double
)