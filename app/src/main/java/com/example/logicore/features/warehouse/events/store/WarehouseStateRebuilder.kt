package com.example.logicore.features.warehouse.events.store

import com.example.logicore.features.warehouse.events.model.WarehouseEvent

data class WarehouseState(

    val stock: MutableMap<String, Double> = mutableMapOf(),
    val orders: MutableList<Int> = mutableListOf()
)

class WarehouseStateRebuilder(
    private val serializer: EventSerializer
) {

    fun rebuild(events: List<StoredWarehouseEvent>): WarehouseState {

        val state = WarehouseState()

        events.forEach { e ->

            when (e.type) {

                "StockChanged" -> {
                    val event = serializer.fromJson(e.type, e.payload)
                            as WarehouseEvent.StockChanged

                    val key = "${event.productId}:${event.locationId}"

                    state.stock[key] =
                        (state.stock[key] ?: 0.0) + event.delta
                }

                "PurchaseOrderCreated" -> {
                    val event = serializer.fromJson(e.type, e.payload)
                            as WarehouseEvent.PurchaseOrderCreated

                    state.orders.add(event.orderId)
                }
            }
        }

        return state
    }
}