package com.example.logicore.routing.engine

data class RouteResult(
    val distanceMeters: Double,
    val durationSeconds: Double,
    val geometry: String? = null
)