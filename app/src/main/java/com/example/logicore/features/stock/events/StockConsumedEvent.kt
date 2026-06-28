package com.example.logicore.features.stock.events

data class StockConsumedEvent(
    val tenantId: String,
    val productId: Int,
    val locationId: Int,
    val quantity: Double,
    val eventId: String
)