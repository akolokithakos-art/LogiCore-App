package com.example.logicore.features.dispatch.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.logicore.features.dispatch.domain.PredictiveDispatchEngine
import com.example.logicore.features.dispatch.domain.model.DispatchPlan
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DispatchViewModel(
    private val engine: PredictiveDispatchEngine
) : ViewModel() {

    private val _plan = MutableStateFlow<List<DispatchPlan>>(emptyList())
    val plan: StateFlow<List<DispatchPlan>> = _plan

    private val vehicleId = 1

    fun generatePlan() {
        viewModelScope.launch {
            _plan.value = engine.generate(vehicleId)
        }
    }
}