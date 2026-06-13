package com.example.logicore.features.vaninventory.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "van_inventory")
data class VanInventoryEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val vehicleId: Int,

    val productId: Int,

    val quantity: Double,

    val reservedQty: Double = 0.0,

    val lastUpdated: Long = System.currentTimeMillis()
)