package com.example.logicore.features.dispatch.domain.model

data class DispatchEvent(
    val vehicleId: Int,
    val type: String, // DELAY / ARRIVAL / STOCK_LOW / REROUTE
    val message: String,
    val timestamp: Long = System.currentTimeMillis()
)