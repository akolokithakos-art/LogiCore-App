package com.example.logicore.features.dispatch.presentation

import androidx.lifecycle.ViewModel
import com.example.logicore.features.dispatch.domain.RealtimeDispatchEngine
import com.example.logicore.features.dispatch.domain.model.DispatchEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class RealtimeDispatchViewModel(
    private val engine: RealtimeDispatchEngine
) : ViewModel() {

    private val _events = MutableStateFlow<List<DispatchEvent>>(emptyList())
    val events: StateFlow<List<DispatchEvent>> = _events

    fun refresh(vehicleId: Int) {
        _events.value = engine.evaluate(vehicleId)
    }
}