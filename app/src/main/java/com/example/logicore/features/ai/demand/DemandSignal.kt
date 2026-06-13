package com.example.logicore.features.ai.demand

data class DemandSignal(

    val productId: Int,

    val routeId: Int? = null,

    val predictedQty: Double,

    val historicalAvg: Double,

    val lastSoldDaysAgo: Int,

    val urgencyScore: Double
)