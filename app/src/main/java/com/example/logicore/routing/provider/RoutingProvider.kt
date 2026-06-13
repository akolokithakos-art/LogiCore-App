package com.logicore.routing.provider

import com.logicore.routing.engine.RouteResult

interface RoutingProvider {

    suspend fun route(
        fromLat: Double,
        fromLng: Double,
        toLat: Double,
        toLng: Double
    ): RouteResult
}