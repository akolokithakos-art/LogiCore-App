package com.example.logicore.routing.osrm

data class OsrmResponse(
    val routes: List<OsrmRoute>
)

data class OsrmRoute(
    val distance: Double,
    val duration: Double,
    val geometry: String? = null
)