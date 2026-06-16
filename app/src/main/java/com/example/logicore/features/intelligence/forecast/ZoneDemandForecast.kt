package com.example.logicore.features.intelligence.forecast

data class ZoneDemandForecast(
    val productId: Int,
    val predictedDemand: Double,
    val confidence: Double
)