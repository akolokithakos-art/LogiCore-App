package com.example.logicore.features.stock.events

data class StockReceivedEvent(
    val tenantId: String,
    val productId: Int,
    val locationId: Int,
    val quantity: Double,
    val eventId: String
)