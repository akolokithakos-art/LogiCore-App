package com.example.logicore.routing.provider

import com.example.logicore.routing.engine.RouteResult

interface RoutingProvider {

    suspend fun route(
        fromLat: Double,
        fromLng: Double,
        toLat: Double,
        toLng: Double
    ): RouteResult
}