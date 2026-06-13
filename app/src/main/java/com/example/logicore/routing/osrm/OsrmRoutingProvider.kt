package com.example.logicore.routing.osrm

import com.example.logicore.routing.engine.RouteResult

class OsrmRoutingProvider {

    suspend fun route(
        fromLat: Double,
        fromLng: Double,
        toLat: Double,
        toLng: Double
    ): RouteResult {

        val coords = "$fromLng,$fromLat;$toLng,$toLat"

        val response = OsrmClient.api.getRoute(coords)

        val route = response.routes.first()

        return RouteResult(
            distanceMeters = route.distance,
            durationSeconds = route.duration,
            geometry = route.geometry
        )
    }
}