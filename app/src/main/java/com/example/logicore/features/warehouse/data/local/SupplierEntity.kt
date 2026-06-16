package com.example.logicore.features.warehouse.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "suppliers")
data class SupplierEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val tenantId: String,

    val code: String,

    val name: String,

    val phone: String? = null,

    val email: String? = null,

    val active: Boolean = true
)