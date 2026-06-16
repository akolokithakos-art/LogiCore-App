package com.example.logicore.features.warehouse.domain.model

data class WarehouseStock(
    val productId: Int,
    val available: Double,
    val reserved: Double,
    val safetyStock: Double
)