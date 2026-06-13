package com.logicore.core.model

data class Driver(
    val id: String,
    val name: String,

    val lat: Double,
    val lng: Double,

    val isAvailable: Boolean = true,

    val rating: Double = 5.0,
    val maxCapacity: Int = 3
)