package com.example.logicore.features.address.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "addresses")
data class AddressEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val tenantId: String,

    val referenceType: String,
    // CUSTOMER
    // WAREHOUSE
    // BRANCH
    // DELIVERY_POINT
    // SUPPLIER

    val referenceId: Int,

    val street: String,
    val number: String,

    val city: String,
    val postalCode: String?,

    val latitude: Double?,
    val longitude: Double?,

    val createdAt: Long =
        System.currentTimeMillis()
)