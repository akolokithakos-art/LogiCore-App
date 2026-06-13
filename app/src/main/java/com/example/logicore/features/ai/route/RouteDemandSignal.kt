package com.example.logicore.features.ai.route

data class RouteDemandSignal(

    val routeId: Int,

    val productId: Int,

    val predictedQty: Double,

    val customerCount: Int,

    val avgSalesPerStop: Double,

    val lastRouteVisitDaysAgo: Int
)