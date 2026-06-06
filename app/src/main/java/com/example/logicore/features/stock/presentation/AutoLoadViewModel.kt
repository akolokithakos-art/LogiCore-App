package com.example.logicore.features.stock.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.logicore.features.stock.domain.AutoLoadOptimizer
import com.example.logicore.features.stock.domain.model.LoadSuggestion
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AutoLoadViewModel(
    private val engine: AutoLoadOptimizer
) : ViewModel() {

    private val _plan = MutableStateFlow<List<LoadSuggestion>>(emptyList())
    val plan: StateFlow<List<LoadSuggestion>> = _plan

    private val vehicleId = 1

    fun generate() {
        viewModelScope.launch {
            _plan.value = engine.generatePlan(vehicleId)
        }
    }
}