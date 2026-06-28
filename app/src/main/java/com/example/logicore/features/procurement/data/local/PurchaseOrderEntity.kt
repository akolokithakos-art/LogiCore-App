package com.example.logicore.features.procurement.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "purchase_orders")
data class PurchaseOrderEntity(
    @PrimaryKey
    val id: String,
    val tenantId: String,
    val supplierId: String,
    val status: String,
    val totalItems: Int,
    val createdAt: Long = System.currentTimeMillis()
)
