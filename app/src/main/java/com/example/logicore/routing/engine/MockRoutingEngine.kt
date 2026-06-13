package com.logicore.routing.engine

import kotlin.math.*

class MockRoutingEngine : RoutingEngine {

    override suspend fun getRoute(
        fromLat: Double,
        fromLng: Double,
        toLat: Double,
        toLng: Double
    ): RouteResult {

        val distanceKm = haversine(fromLat, fromLng, toLat, toLng)

        val roadFactor = 1.25 // real roads ≠ straight line

        val distanceMeters = distanceKm * 1000 * roadFactor

        val avgSpeedMps = 12.5 // ~45 km/h

        val durationSeconds = distanceMeters / avgSpeedMps

        return RouteResult(
            distanceMeters = distanceMeters,
            durationSeconds = durationSeconds
        )
    }

    private fun haversine(
        lat1: Double,
        lon1: Double,
        lat2: Double,
        lon2: Double
    ): Double {

        val R = 6371.0

        val dLat = Math.toRadians(lat2 - lat1)
        val dLon = Math.toRadians(lon2 - lon1)

        val a = sin(dLat / 2).pow(2.0) +
                cos(Math.toRadians(lat1)) *
                cos(Math.toRadians(lat2)) *
                sin(dLon / 2).pow(2.0)

        val c = 2 * atan2(sqrt(a), sqrt(1 - a))

        return R * c
    }
}