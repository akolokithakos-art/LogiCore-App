package com.example.logicore.features.dispatch.realtime

sealed class DispatchEvent {

    data class LocationUpdate(
        val vehicleId: Int,
        val lat: Double,
        val lng: Double
    ) : DispatchEvent()

    data class StockLow(
        val vehicleId: Int,
        val productId: Int
    ) : DispatchEvent()

    data class NewOrder(
        val customerId: Int,
        val productId: Int,
        val qty: Double
    ) : DispatchEvent()

    data class RouteDeviation(
        val vehicleId: Int,
        val deviationMeters: Double
    ) : DispatchEvent()
}