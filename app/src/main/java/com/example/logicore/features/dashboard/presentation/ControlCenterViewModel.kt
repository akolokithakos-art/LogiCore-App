package com.example.logicore.features.dashboard.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.logicore.features.dashboard.state.ControlCenterState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ControlCenterViewModel : ViewModel() {

    private val _state =
        MutableStateFlow(ControlCenterState())

    val state: StateFlow<ControlCenterState> = _state

    fun updateVehicles(list: List<Any>) {

        viewModelScope.launch {

            _state.value =
                _state.value.copy(
                    alerts = _state.value.alerts + "Fleet updated"
                )
        }
    }

    fun addAlert(message: String) {

        _state.value =
            _state.value.copy(
                alerts = _state.value.alerts + message
            )
    }

    fun addAIDecision(decision: String) {

        _state.value =
            _state.value.copy(
                aiDecisions = _state.value.aiDecisions + decision
            )
    }
}