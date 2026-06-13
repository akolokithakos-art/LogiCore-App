package com.example.logicore.features.sales.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sales")
data class SaleEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val tenantId: String,

    val customerId: Int,

    val total: Double = 0.0,

    val status: String = "DRAFT", // DRAFT / CONFIRMED / SYNCED

    val createdAt: Long = System.currentTimeMillis()
)