package com.example.logicore.features.warehouse.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "purchase_order_lines")
data class PurchaseOrderLineEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val purchaseOrderId: Int,

    val productId: Int,

    val quantity: Double
)