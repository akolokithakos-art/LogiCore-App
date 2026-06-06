package com.example.logicore.features.dispatch.domain

import com.example.logicore.features.dispatch.domain.model.DispatchEvent
import com.example.logicore.features.tracking.data.VehicleStateStore
import com.example.logicore.features.stock.domain.StockEngine

class RealtimeDispatchEngine(
    private val stateStore: VehicleStateStore,
    private val stockEngine: StockEngine
) {

    fun evaluate(vehicleId: Int): List<DispatchEvent> {

        val state = stateStore.get(vehicleId)
            ?: return emptyList()

        val events = mutableListOf<DispatchEvent>()

        // 1. LOW STOCK CHECK
        if (state.activeOrderId == null) {
            events.add(
                DispatchEvent(
                    vehicleId,
                    "IDLE",
                    "Vehicle is idle - consider reload"
                )
            )
        }

        // 2. SPEED CHECK (delay detection)
        if (state.speed < 5.0) {
            events.add(
                DispatchEvent(
                    vehicleId,
                    "DELAY",
                    "Vehicle moving slowly or stopped"
                )
            )
        }

        // 3. STOCK RISK CHECK (example simplified)
        val lowStockRisk = true // placeholder logic

        if (lowStockRisk) {
            events.add(
                DispatchEvent(
                    vehicleId,
                    "STOCK_LOW",
                    "Potential stock shortage detected"
                )
            )
        }

        return events
    }
}