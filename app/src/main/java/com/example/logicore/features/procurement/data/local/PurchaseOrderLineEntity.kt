package com.example.logicore.features.procurement.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "purchase_order_lines")
data class PurchaseOrderLineEntity(
    @PrimaryKey
    val id: String,
    val tenantId: String,
    val purchaseOrderId: String,
    val productId: String,
    val quantity: Double
)
