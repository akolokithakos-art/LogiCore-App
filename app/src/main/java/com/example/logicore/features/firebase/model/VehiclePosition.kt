package com.example.logicore.features.firebase.model

data class VehiclePosition(

    val latitude: Double,

    val longitude: Double,

    val speedKmh: Double = 0.0,

    val heading: Double? = null,

    val accuracyMeters: Float? = null
)