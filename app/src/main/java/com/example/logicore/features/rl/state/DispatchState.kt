package com.example.logicore.features.rl.state

data class DispatchState(

    val distanceKm: Double,
    val traffic: Double,
    val driverLoad: Double,
    val vehicleCapacity: Double,
    val slaUrgency: Double
)