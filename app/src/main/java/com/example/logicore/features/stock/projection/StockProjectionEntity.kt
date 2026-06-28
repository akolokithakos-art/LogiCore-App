package com.example.logicore.features.stock.projection

import androidx.room.Entity

@Entity(
    tableName = "stock_projection",
    primaryKeys = [
        "tenantId",
        "productId",
        "locationId"
    ]
)
data class StockProjectionEntity(

    val tenantId: String,

    val productId: Int,

    val locationId: Int,

    val quantity: Double,

    val reorderThreshold: Double = 0.0,

    val averageConsumptionPerDay: Double = 0.0,

    val trendFactor: Double = 1.0,

    val updatedAt: Long =
        System.currentTimeMillis()
)