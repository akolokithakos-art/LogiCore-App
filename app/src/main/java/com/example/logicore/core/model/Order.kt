package com.logicore.core.model

data class Order(
    val id: String,

    val lat: Double,
    val lng: Double,

    val priority: Int = 1,

    val serviceTimeMinutes: Int = 10,

    val slaMinutes: Double = 30.0,

    val tenantId: String,

    val isAssigned: Boolean = false
)