package com.example.logicore.features.sales.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sale_lines")
data class SaleLineEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val tenantId: String,

    val saleId: Int,

    val productId: Int,

    val quantity: Double,

    val price: Double
)