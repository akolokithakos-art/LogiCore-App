package com.example.logicore.features.ai.decision

import com.example.logicore.features.warehouse.events.model.WarehouseEvent
import com.example.logicore.features.core.events.EventBus

class DecisionEngine(
    private val bus: EventBus
) {

    suspend fun evaluate(
        productId: Int,
        forecast: Double,
        stock: Double
    ) {

        val safetyStock = forecast * 1.2
        val reorderPoint = forecast * 0.7

        if (stock < reorderPoint) {

            val qty = safetyStock - stock

            bus.publish(
                WarehouseEvent.ReorderTriggered(
                    productId = productId,
                    suggestedQty = qty
                )
            )
        }
    }
}