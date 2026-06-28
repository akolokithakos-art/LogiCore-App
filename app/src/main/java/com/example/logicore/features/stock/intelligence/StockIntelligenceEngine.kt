package com.example.logicore.features.stock.intelligence

import com.example.logicore.features.core.events.EventBus
import com.example.logicore.features.warehouse.events.model.WarehouseEvent
import kotlin.math.abs

class StockIntelligenceEngine(
    private val bus: EventBus
) {

    suspend fun analyze(
        productId: Int,
        expected: Double,
        actual: Double
    ) {

        val deviation = abs(actual - expected) / (expected + 0.0001)

        if (deviation > 0.5) {

            bus.publish(
                WarehouseEvent.StockAnomalyDetected(
                    productId = productId,
                    severity = deviation,
                    expected = expected,
                    actual = actual
                )
            )
        }
    }
}