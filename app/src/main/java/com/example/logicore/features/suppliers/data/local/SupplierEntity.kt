package com.example.logicore.features.suppliers.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "suppliers")
data class SupplierEntity(
    @PrimaryKey
    val id: String,
    val tenantId: String,
    val code: String,
    val name: String,
    val phone: String?,
    val email: String?,
    val active: Boolean = true
)
