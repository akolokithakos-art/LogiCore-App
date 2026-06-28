package com.example.logicore.features.tenant.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "tenants"
)
data class TenantEntity(

    @PrimaryKey
    val id: String,

    val companyName: String,

    val vatNumber: String,

    val address: String,

    val phone: String,

    val email: String,

    val active: Boolean = true,

    val createdAt: Long =
        System.currentTimeMillis()
)