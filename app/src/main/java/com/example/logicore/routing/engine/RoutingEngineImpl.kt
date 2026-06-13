package com.logicore.routing.engine

import com.logicore.routing.provider.RoutingProvider

class RoutingEngineImpl(
    private val provider: OsrmRoutingProvider
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