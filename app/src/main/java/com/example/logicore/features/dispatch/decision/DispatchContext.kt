package com.example.logicore.features.dispatch.decision

data class DispatchContext(

    val vehicleId: Int,

    val remainingCapacity: Double,

    val lowStockProducts: Int,

    val routeDeviationMeters: Double,

    val pendingOrders: Int,

    val timeOnRouteMinutes: Int
)