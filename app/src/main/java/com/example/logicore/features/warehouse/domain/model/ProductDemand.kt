package com.example.logicore.features.warehouse.domain.model

data class ProductDemand(
    val productId: Int,
    val dailyAvg: Double,
    val trendFactor: Double,
    val riskFactor: Double
)