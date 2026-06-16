package com.example.logicore.features.warehouse.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "purchase_orders")
data class PurchaseOrderEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val tenantId: String,

    val supplierId: Int,

    val status: String, // DRAFT / SENT / CONFIRMED / RECEIVED

    val createdAt: Long = System.currentTimeMillis()
)