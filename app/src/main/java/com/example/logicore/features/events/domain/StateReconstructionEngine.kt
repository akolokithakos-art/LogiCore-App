package com.example.logicore.features.events.domain

import com.example.logicore.features.events.data.local.EventStoreDao
import com.example.logicore.features.events.data.local.EventStoreEntity
import com.google.gson.Gson
import com.example.logicore.features.procurement.events.ProcurementEvent
import com.example.logicore.features.warehouse.events.model.WarehouseEvent
import kotlinx.coroutines.flow.first

class StateReconstructionEngine(
    private val dao: EventStoreDao,
    private val gson: Gson
) {

    suspend fun rebuildStockState(tenantId: String): Map<Int, Double> {

        val events: List<EventStoreEntity> =
            dao.streamAll(tenantId).first()

        val stockMap = mutableMapOf<Int, Double>()

        events.forEach { event ->

            when (event.eventType) {

                ProcurementEvent.OrderReceived::class.simpleName -> {
                    // inbound stock handled elsewhere if needed
                }

                WarehouseEvent.StockReceived::class.simpleName -> {

                    val data = gson.fromJson(
                        event.payload,
                        WarehouseEvent.StockReceived::class.java
                    )

                    val current = stockMap[data.productId] ?: 0.0

                    stockMap[data.productId] =
                        current + data.quantity
                }
            }
        }

        return stockMap
    }
}