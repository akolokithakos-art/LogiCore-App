package com.example.logicore.features.products.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class ProductEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val tenantId: String,

    val code: String,

    val barcode: String,

    val name: String,

    val price: Double,

    val stock: Double,
)