package com.example.logicore.features.dispatch.engine

import com.example.logicore.features.fleet.data.local.DriverDao
import com.example.logicore.features.fleet.data.local.VehicleDao
import com.example.logicore.features.fleet.data.local.DriverVehicleAssignmentDao
import com.example.logicore.features.dispatch.domain.DispatchRequest
import com.example.logicore.features.dispatch.domain.DispatchDecision

class DispatchEngine(

    private val driverDao: DriverDao,
    private val vehicleDao: VehicleDao,
    private val assignmentDao: DriverVehicleAssignmentDao

) {

    suspend fun dispatch(request: DispatchRequest): DispatchDecision? {

        val drivers = driverDao.getAll(request.tenantId)

        var best: DispatchDecision? = null

        for (driver in drivers) {

            val assignment = assignmentDao.findActiveVehicleForDriver(
                request.tenantId,
                driver.id
            ) ?: continue

            val vehicleId = assignment.vehicleId

            val score = calculateScore(driver.id, vehicleId, request)

            if (best == null || score > best.score) {

                best = DispatchDecision(
                    driverId = driver.id,
                    vehicleId = vehicleId,
                    score = score,
                    reason = "heuristic-match"
                )
            }
        }

        return best
    }

    private fun calculateScore(
        driverId: String,
        vehicleId: String,
        request: DispatchRequest
    ): Double {

        // SIMPLE BASE HEURISTIC (upgrade later to RL)
        var score = 1.0

        if (request.priority > 5) score += 1.5

        if (request.requiredCapacity != null) {
            score += 0.5
        }

        return score
    }
}