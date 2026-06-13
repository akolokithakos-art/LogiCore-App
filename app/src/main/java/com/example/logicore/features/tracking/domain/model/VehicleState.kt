package com.example.logicore.features.tracking.domain.model

data class VehicleState(
    val tenantId: String,
    val vehicleId: Int,
    val lat: Double,
    val lng: Double,
    val speed: Double,
    val lastUpdate: Long,
    val activeOrderId: Int?
)