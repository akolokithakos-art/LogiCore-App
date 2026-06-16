package com.example.logicore.features.warehouse.domain.model

data class WarehouseDemandInput(
    val productId: Int,
    val source: String, // VAN / BRANCH / FORECAST / LOSS
    val quantity: Double,
    val timestamp: Long
)