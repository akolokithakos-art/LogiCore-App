package com.example.logicore.routing.engine

interface RoutingEngine {

    suspend fun getRoute(
        fromLat: Double,
        fromLng: Double,
        toLat: Double,
        toLng: Double
    ): RouteResult
}