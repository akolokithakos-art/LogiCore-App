package com.example.logicore.features.firebase.model

data class RemoteDispatchState(

    val tenantId: String,

    val vehicleId: Int,

    val lat: Double,
    val lng: Double,

    val routeId: Int?,

    val speed: Double,

    val remainingCapacity: Double,

    val lastSync: Long = System.currentTimeMillis()
)