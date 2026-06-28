package com.example.logicore.features.dispatch.events

data class DispatchAssignedEvent(

    val tenantId: String,
    val orderId: String,

    val driverId: String,
    val vehicleId: String,

    val timestamp: Long = System.currentTimeMillis()
)