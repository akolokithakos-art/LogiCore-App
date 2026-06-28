package com.example.logicore.features.dispatch.domain

data class DispatchDecision(

    val driverId: String,

    val vehicleId: String,

    val score: Double,

    val reason: String
)