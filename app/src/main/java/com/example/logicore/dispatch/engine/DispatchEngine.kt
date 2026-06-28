package com.example.logicore.dispatch.engine

import com.example.logicore.core.model.Driver
import com.example.logicore.core.model.Order
import com.example.logicore.routing.engine.RoutingEngine
import com.example.logicore.eta.engine.EtaEngine
import com.example.logicore.dispatch.engine.DriverStateTracker

data class Assignment(
    val orderId: String,
    val driverId: String,
    val etaMinutes: Double
)

data class DispatchScore(
    val driverId: String,
    val orderId: String,
    val etaMinutes: Double,
    val distanceMeters: Double,
    val score: Double
)

class DispatchEngine(
    private val routingEngine: RoutingEngine,
    private val etaEngine: EtaEngine,
    private val stateTracker: DriverStateTracker
) {

    suspend fun assign(
        orders: List<Order>,
        drivers: List<Driver>
    ): List<Assignment> {

        val availableDrivers = drivers.filter { it.isAvailable }.toMutableList()
        val result = mutableListOf<Assignment>()

        for (order in orders.sortedByDescending { it.priority }) {

            val scoredDrivers = availableDrivers.map { driver ->

                val route = routingEngine.getRoute(
                    fromLat = driver.lat,
                    fromLng = driver.lng,
                    toLat = order.lat,
                    toLng = order.lng
                )

                val eta = etaEngine.calculateEtaMinutes(
                    route = route,
                    trafficMultiplier = 1.0,
                    driverFactor = 1.0,
                    weatherMultiplier = 1.0
                )

                val load = stateTracker.getNormalizedLoad(driver.id)

                val score = calculateScore(
                    etaMinutes = eta,
                    distanceMeters = route.distanceMeters,
                    driverLoad = load
                )

                DispatchScore(
                    driverId = driver.id,
                    orderId = order.id,
                    etaMinutes = eta,
                    distanceMeters = route.distanceMeters,
                    score = score
                )
            }

            val best = scoredDrivers.minByOrNull { it.score } ?: continue

            result.add(
                Assignment(
                    orderId = best.orderId,
                    driverId = best.driverId,
                    etaMinutes = best.etaMinutes
                )
            )

            stateTracker.increaseLoad(best.driverId)
            availableDrivers.removeIf { it.id == best.driverId }
        }

        return result
    }

    private fun calculateScore(
        etaMinutes: Double,
        distanceMeters: Double,
        driverLoad: Double
    ): Double {

        // weighted cost function (lower = better)
        return (etaMinutes * 0.7) +
                (distanceMeters / 1000.0 * 0.2) +
                (driverLoad * 10.0)
    }
}