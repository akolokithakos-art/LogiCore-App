package com.example.logicore.features.ml.core

import com.example.logicore.features.warehouse.events.WarehouseEventBus
import com.example.logicore.features.warehouse.events.model.WarehouseEvent

class MlEventBridge(
    private val bus: WarehouseEventBus
) {

    fun emitForecast(productId: Int, forecast: Double) {

        bus.publish(
            WarehouseEvent.ForecastGenerated(
                productId = productId,
                forecast30Days = forecast
            )
        )
    }
}