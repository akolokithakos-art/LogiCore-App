package com.example.logicore.features.firebase.model

data class SyncMetadata(

    val tenantId: String,

    val vehicleId: Int,

    val driverId: Int? = null,

    val lastSync: Long
)