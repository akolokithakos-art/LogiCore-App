package com.example.logicore.features.stock.intelligence

import com.example.logicore.features.core.events.EventBus
import com.example.logicore.features.warehouse.events.model.WarehouseEvent

class DeadStockDetector(
    private val bus: EventBus
) {

    suspend fun evaluate(
        productId: Int,
        movementDays: Int
    ) {

        if (movementDays > 30) {

            bus.publish(
                WarehouseEvent.ReorderTriggered(
                    productId = productId,
                    suggestedQty = 100.0
                )
            )
        }
    }
}