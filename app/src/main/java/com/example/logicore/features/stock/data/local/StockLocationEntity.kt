package com.example.logicore.features.stock.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stock_locations")
data class StockLocationEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val tenantId: String,

    val name: String,

    val type: String // WAREHOUSE / VEHICLE / BRANCH
)