package com.logicore.routing.provider

import com.logicore.routing.engine.RouteResult
import kotlin.math.*

class MockRoutingProvider : RoutingProvider {

    override suspend fun route(
        fromLat: Double,
        fromLng: Double,
        toLat: Double,
        toLng: Double
    ): RouteResult {

        val R = 6371.0

        val dLat = Math.toRadians(toLat - fromLat)
        val dLng = Math.toRadians(toLng - fromLng)

        val a = sin(dLat / 2).pow(2.0) +
                cos(Math.toRadians(fromLat)) *
                cos(Math.toRadians(toLat)) *
                sin(dLng / 2).pow(2.0)

        val c = 2 * atan2(sqrt(a), sqrt(1 - a))

        val km = R * c
        val meters = km * 1000 * 1.25

        return RouteResult(
            distanceMeters = meters,
            durationSeconds = meters / 12.5
        )
    }
}