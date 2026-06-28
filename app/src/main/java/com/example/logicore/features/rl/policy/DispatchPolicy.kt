package com.example.logicore.features.rl.policy

import com.example.logicore.features.rl.state.DispatchState
import com.example.logicore.features.rl.action.DispatchAction

class DispatchPolicy {

    fun selectAction(state: DispatchState): DispatchAction {

        // placeholder policy (to be replaced by model)
        return DispatchAction(
            driverId = "best_driver",
            vehicleId = "best_vehicle"
        )
    }
}