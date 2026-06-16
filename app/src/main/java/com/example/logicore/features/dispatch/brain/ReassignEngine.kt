package com.example.logicore.features.dispatch.brain

import com.example.logicore.core.model.Driver
import com.example.logicore.core.model.Order
import com.example.logicore.dispatch.engine.DriverStateTracker

class ReassignEngine(
    private val driverStateTracker: DriverStateTracker
) {

    fun shouldReassign(
        driver: Driver,
        currentLoad: Int,
        etaMinutes: Double
    ): Boolean {

        val loadPressure = driverStateTracker.getNormalizedLoad(driver.id)

        return when {
            etaMinutes > 45 -> true
            loadPressure > 0.8 -> true
            currentLoad >= 3 -> true
            else -> false
        }
    }
}