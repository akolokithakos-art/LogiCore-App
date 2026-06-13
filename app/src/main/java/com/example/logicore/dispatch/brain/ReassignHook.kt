package com.logicore.dispatch.brain

import com.example.logicore.dispatch.engine.DriverStateTracker
import com.logicore.core.model.Driver

class ReassignHook(
    private val driverStateTracker: DriverStateTracker
) {

    fun needsReassign(
        driver: Driver,
        etaMinutes: Double
    ): Boolean {

        val load = driverStateTracker.getNormalizedLoad(driver.id)

        return when {
            etaMinutes > 40 -> true
            load > 0.85 -> true
            !driver.isAvailable -> true
            else -> false
        }
    }
}