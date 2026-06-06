package com.example.logicore.features.ai.domain.model

data class ZoneDemand(
    val productId: Int,
    val zoneId: Int,
    val avgDemand: Double,
    val recommendedLoad: Double
)