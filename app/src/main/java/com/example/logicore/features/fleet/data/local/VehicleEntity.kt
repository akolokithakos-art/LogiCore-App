package com.example.logicore.features.fleet.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "vehicles"
)
data class VehicleEntity(

    @PrimaryKey
    val id: String,

    val tenantId: String,

    val code: String,

    val plateNumber: String,

    val brand: String,

    val model: String,

    val capacityKg: Double,

    val capacityM3: Double,

    val active: Boolean = true,

    val createdAt: Long =
        System.currentTimeMillis()
)