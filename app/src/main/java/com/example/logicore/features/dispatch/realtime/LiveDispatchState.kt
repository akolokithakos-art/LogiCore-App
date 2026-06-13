package com.example.logicore.features.dispatch.realtime

data class LiveDispatchState(

    val vehicleId: Int,

    val latitude: Double,
    val longitude: Double,

    val speed: Double,

    val currentRouteId: Int?,

    val remainingStops: Int,

    val remainingCapacity: Double,

    val lastUpdate: Long = System.currentTimeMillis()
)