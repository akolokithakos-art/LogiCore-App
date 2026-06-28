package com.example.logicore.features.brain.perception

import com.example.logicore.features.rl.state.DispatchState
import com.example.logicore.features.dispatch.domain.DispatchRequest

class PerceptionLayer {

    fun buildState(request: DispatchRequest): DispatchState {

        return DispatchState(
            distanceKm = 0.0, // from routing engine
            traffic = 0.0,
            driverLoad = 0.0,
            vehicleCapacity = request.requiredCapacity ?: 0.0,
            slaUrgency = request.priority.toDouble()
        )
    }
}