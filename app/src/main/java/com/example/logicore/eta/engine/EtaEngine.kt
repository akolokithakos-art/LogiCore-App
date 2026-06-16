package com.example.logicore.eta.engine

import com.example.logicore.routing.engine.RouteResult

/**
 * ETA engine v1 - converts route + multipliers into final ETA
 */
class EtaEngine {

    fun calculateEtaMinutes(
        route: RouteResult,
        trafficMultiplier: Double = 1.0,
        driverFactor: Double = 1.0,
        weatherMultiplier: Double = 1.0
    ): Double {

        val baseMinutes = route.durationSeconds / 60.0

        return baseMinutes *
                trafficMultiplier *
                driverFactor *
                weatherMultiplier
    }
}