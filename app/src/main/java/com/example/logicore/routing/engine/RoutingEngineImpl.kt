package com.example.logicore.routing.engine

import com.example.logicore.routing.osrm.OsrmRoutingProvider

class RoutingEngineImpl(
    private val provider: OsrmRoutingProvider,
) : RoutingEngine {

    override suspend fun getRoute(
        fromLat: Double,
        fromLng: Double,
        toLat: Double,
        toLng: Double
    ): RouteResult {

        return provider.route(fromLat, fromLng, toLat, toLng)
    }
}