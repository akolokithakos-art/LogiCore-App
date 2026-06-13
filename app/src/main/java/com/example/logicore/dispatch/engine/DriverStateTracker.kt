package com.example.logicore.dispatch.engine

import java.util.concurrent.ConcurrentHashMap

class DriverStateTracker {

    // load (πόσες αναθέσεις έχει ο driver)
    private val loadMap = ConcurrentHashMap<String, Int>()

    // live position (lat, lng)
    private val positionMap = ConcurrentHashMap<String, Pair<Double, Double>>()

    fun getLoad(driverId: String): Int {
        return loadMap[driverId] ?: 0
    }

    fun increaseLoad(driverId: String) {
        loadMap[driverId] = getLoad(driverId) + 1
    }

    fun decreaseLoad(driverId: String) {
        val current = getLoad(driverId)
        loadMap[driverId] = (current - 1).coerceAtLeast(0)
    }

    fun resetLoad() {
        loadMap.clear()
    }

    fun updatePosition(driverId: String, lat: Double, lng: Double) {
        positionMap[driverId] = lat to lng
    }

    fun getPosition(driverId: String): Pair<Double, Double>? {
        return positionMap[driverId]
    }

    fun resetPositions() {
        positionMap.clear()
    }

    fun getNormalizedLoad(driverId: String, maxLoad: Int = 5): Double {
        return (getLoad(driverId).toDouble() / maxLoad).coerceIn(0.0, 1.0)
    }

    fun snapshotLoads(): Map<String, Int> {
        return loadMap.toMap()
    }

    fun snapshotPositions(): Map<String, Pair<Double, Double>> {
        return positionMap.toMap()
    }
}