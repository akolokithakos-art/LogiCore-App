package com.example.logicore.features.fleet.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "driver_vehicle_assignments"
)
data class DriverVehicleAssignmentEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val tenantId: String,

    val driverId: String,

    val vehicleId: String,

    val assignedAt: Long =
        System.currentTimeMillis(),

    val active: Boolean = true
)