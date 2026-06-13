package com.example.logicore.map.live

import kotlinx.coroutines.flow.MutableStateFlow

data class DriverPosition(
    val driverId: String,
    val lat: Double,
    val lng: Double
)

class DriverLocationManager {

    val driverPositions = MutableStateFlow<Map<String, DriverPosition>>(emptyMap())

    fun updatePosition(position: DriverPosition) {

        val updated = driverPositions.value.toMutableMap()
        updated[position.driverId] = position

        driverPositions.value = updated
    }
}