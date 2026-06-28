package com.example.logicore.features.procurement.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "supplier_products"
)
data class SupplierProductEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val tenantId: String,

    val supplierId: String,

    val productId: String,

    val supplierProductCode: String?,

    val supplierProductName: String?,

    val lastPurchasePrice: Double,

    val preferredSupplier: Boolean = false,

    val active: Boolean = true,

    val updatedAt: Long =
        System.currentTimeMillis()
)