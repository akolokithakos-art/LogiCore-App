package com.example.logicore.features.events.domain.model

data class WarehouseSnapshot(
    val productId: Int,
    val locationId: Int,
    val stock: Double
)