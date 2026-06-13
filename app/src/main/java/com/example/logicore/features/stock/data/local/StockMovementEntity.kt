package com.example.logicore.features.stock.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stock_movements")
data class StockMovementEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val tenantId: String,

    val productId: Int,

    val fromLocationId: Int?, // null = εισαγωγή

    val toLocationId: Int?,   // null = έξοδος

    val quantity: Double,

    val type: String, // SALE / TRANSFER / LOAD / RETURN / ADJUST

    val createdAt: Long = System.currentTimeMillis()
)