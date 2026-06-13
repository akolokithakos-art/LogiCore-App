package com.example.logicore.features.dispatch.realtime

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class RealTimeDispatchEngine {

    private val _state =
        MutableStateFlow<List<LiveDispatchState>>(emptyList())

    val state: StateFlow<List<LiveDispatchState>> =
        _state

    fun updateState(newState: LiveDispatchState) {

        val updated =
            _state.value.toMutableList()

        updated.removeAll { it.vehicleId == newState.vehicleId }
        updated.add(newState)

        _state.value = updated
    }

    fun processEvent(event: DispatchEvent) {

        when (event) {

            is DispatchEvent.LocationUpdate -> {
                // update GPS state only
            }

            is DispatchEvent.StockLow -> {
                // trigger reload logic
            }

            is DispatchEvent.NewOrder -> {
                // trigger immediate validation
            }

            is DispatchEvent.RouteDeviation -> {
                // trigger reroute logic
            }
        }
    }
}