package com.example.logicore.features.tracking.data

import com.example.logicore.features.tracking.domain.model.VehicleState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class VehicleStateStore {

    private val _states =
        MutableStateFlow<Map<Int, VehicleState>>(emptyMap())

    val states: StateFlow<Map<Int, VehicleState>> = _states

    fun update(state: VehicleState) {

        _states.value = _states.value.toMutableMap().apply {
            put(state.vehicleId, state)
        }
    }

    fun get(vehicleId: Int): VehicleState? {
        return _states.value[vehicleId]
    }
}