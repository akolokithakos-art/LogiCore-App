package com.example.logicore.features.dashboard.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.logicore.features.firebase.model.RemoteRoutePlan
import com.example.logicore.features.firebase.repository.FirebaseRouteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DriverDashboardViewModel(
    private val routeRepository: FirebaseRouteRepository,
    private val driverId: String = "driver-1" // Mock ID for now
) : ViewModel() {

    private val _assignedRoute = MutableStateFlow<RemoteRoutePlan?>(null)
    val assignedRoute: StateFlow<RemoteRoutePlan?> = _assignedRoute

    init {
        observeRoute()
    }

    private fun observeRoute() {
        viewModelScope.launch {
            routeRepository.observeRoute("tenant-1", driverId).collect {
                _assignedRoute.value = it
            }
        }
    }
}
