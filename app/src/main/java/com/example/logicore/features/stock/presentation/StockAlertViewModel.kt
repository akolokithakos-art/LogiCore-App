package com.example.logicore.features.stock.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.logicore.features.stock.domain.StockAlertEngine
import com.example.logicore.features.stock.domain.model.StockAlert
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class StockAlertViewModel(
    private val engine: StockAlertEngine
) : ViewModel() {

    private val _alerts = MutableStateFlow<List<StockAlert>>(emptyList())
    val alerts: StateFlow<List<StockAlert>> = _alerts

    private val vanId = 1

    fun refresh() {
        viewModelScope.launch {
            _alerts.value = engine.generateAlerts(vanId)
        }
    }
}