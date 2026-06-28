package com.example.logicore.features.procurement.events.subscribers

import com.example.logicore.features.core.events.EventSubscriber
import com.example.logicore.features.procurement.domain.ProcurementEngine
import com.example.logicore.features.warehouse.events.model.WarehouseEvent

class ProcurementSubscriber(
    private val engine: ProcurementEngine
) : EventSubscriber<WarehouseEvent> {

    override suspend fun handle(event: WarehouseEvent) {

        when (event) {

            is WarehouseEvent.ReorderTriggered -> {

                engine.createOrder(
                    productId = event.productId,
                    qty = event.suggestedQty
                )
            }

            else -> {
                // no-op for other events
            }
        }
    }
}