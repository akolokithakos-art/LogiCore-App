package com.example.logicore.features.fleet.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "drivers"
)
data class DriverEntity(

    @PrimaryKey
    val id: String,

    val tenantId: String,

    val employeeCode: String,

    val firstName: String,

    val lastName: String,

    val phone: String,

    val email: String?,

    val licenseNumber: String,

    val active: Boolean = true,

    val createdAt: Long =
        System.currentTimeMillis()
)