package com.example.logicore.features.ml.events

sealed class MlTrainingEvent {

    data class DemandSignal(
        val productId: Int,
        val locationId: Int,
        val quantity: Double,
        val timestamp: Long
    ) : MlTrainingEvent()

    data class StockOutcome(
        val productId: Int,
        val locationId: Int,
        val before: Double,
        val after: Double
    ) : MlTrainingEvent()

    data class DeliveryOutcome(
        val vehicleId: Int,
        val delayMinutes: Int,
        val success: Boolean
    ) : MlTrainingEvent()
}