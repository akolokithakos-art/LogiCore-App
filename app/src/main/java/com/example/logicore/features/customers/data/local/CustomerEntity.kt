package com.example.logicore.features.customers.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "customers")
data class CustomerEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val tenantId: String,

    val code: String = "",

    val name: String,

    val phone: String = "",

    val email: String = "",

    val address: String = "",

    val creditLimit: Double = 0.0,

    val balance: Double = 0.0,

    val active: Boolean = true,
)