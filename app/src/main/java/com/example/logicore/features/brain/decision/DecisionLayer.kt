package com.example.logicore.features.brain.decision

import com.example.logicore.features.dispatch.domain.DispatchDecision

class DecisionLayer {

    fun select(state: Any, prediction: Any): DispatchDecision {

        return DispatchDecision(
            driverId = "best_driver",
            vehicleId = "best_vehicle",
            score = 0.95,
            reason = "brain-selection"
        )
    }
}