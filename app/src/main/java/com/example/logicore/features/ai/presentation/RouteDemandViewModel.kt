package com.example.logicore.features.ai.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.logicore.features.ai.domain.RouteDemandAI
import com.example.logicore.features.ai.domain.model.ZoneDemand
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RouteDemandViewModel(
    private val ai: RouteDemandAI
) : ViewModel() {

    private val _result = MutableStateFlow<List<ZoneDemand>>(emptyList())
    val result: StateFlow<List<ZoneDemand>> = _result

    private val vehicleId = 1

    fun runAnalysis() {
        viewModelScope.launch {
            _result.value = ai.analyze(vehicleId)
        }
    }
}