package com.example.logicore.features.dispatch.domain

data class DispatchRequest(

    val tenantId: String,

    val orderId: String,

    val pickupLat: Double,
    val pickupLng: Double,

    val dropLat: Double,
    val dropLng: Double,

    val priority: Int = 0,

    val requiredCapacity: Double? = null
)