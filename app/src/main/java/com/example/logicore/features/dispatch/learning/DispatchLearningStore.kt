package com.example.logicore.features.dispatch.learning

import com.example.logicore.features.dispatch.learning.model.DispatchFeedbackEvent

class DispatchLearningStore {

    private val events = mutableListOf<DispatchFeedbackEvent>()

    fun record(event: DispatchFeedbackEvent) {
        events.add(event)
    }

    fun getAll(): List<DispatchFeedbackEvent> = events

    fun getVehicleEvents(vehicleId: Int): List<DispatchFeedbackEvent> {
        return events.filter { it.vehicleId == vehicleId }
    }
}