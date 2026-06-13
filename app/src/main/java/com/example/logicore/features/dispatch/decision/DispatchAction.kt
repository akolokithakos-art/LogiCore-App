package com.example.logicore.features.dispatch.decision

sealed class DispatchAction {

    data class ContinueRoute(
        val vehicleId: Int
    ) : DispatchAction()

    data class Reroute(
        val vehicleId: Int,
        val newRouteId: Int
    ) : DispatchAction()

    data class EmergencyReload(
        val vehicleId: Int,
        val warehouseId: Int
    ) : DispatchAction()

    data class StopAndValidate(
        val vehicleId: Int,
        val reason: String
    ) : DispatchAction()
}