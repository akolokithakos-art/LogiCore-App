package com.example.logicore.features.warehouse.domain.model

data class ProductForecast(

    val productId: Int,

    val totalSold: Double,

    val avgDailyDemand: Double,

    val forecast7Days: Double,

    val forecast30Days: Double
)