package com.example.logicore.features.warehouse.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "supplier_products")
data class SupplierProductEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val tenantId: String,

    val supplierId: Int,

    val productId: Int,

    val leadTimeDays: Int,

    val minOrderQty: Double,

    val reorderPoint: Double,

    val safetyStock: Double
)