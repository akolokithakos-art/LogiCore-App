package com.example.logicore.features.stock.events.subscribers

import com.example.logicore.features.core.events.EventSubscriber
import com.example.logicore.features.stock.domain.StockEngine
import com.example.logicore.features.warehouse.events.model.WarehouseEvent

class StockSubscriber(
    private val stockEngine: StockEngine
) : EventSubscriber<WarehouseEvent> {

    override suspend fun handle(event: WarehouseEvent) {

        when (event) {

            is WarehouseEvent.StockReceived -> {

                stockEngine.addStock(
                    productId = event.productId,
                    warehouseId = event.warehouseId,
                    qty = event.quantity
                )
            }

            is WarehouseEvent.StockConsumed -> {

                stockEngine.removeStock(
                    productId = event.productId,
                    qty = event.quantity
                )
            }

            else -> {
                // no-op for unrelated events
            }
        }
    }
}