package com.example.logicore.features.stock.events

import com.example.logicore.features.core.events.EventBus
import com.example.logicore.features.procurement.events.ProcurementEvent
import com.example.logicore.features.stock.domain.StockEngine

class StockEventListener(
    private val eventBus: EventBus,
    private val stockEngine: StockEngine
) {

    suspend fun startListening() {

        eventBus.events.collect { event ->

            when (event) {

                is ProcurementEvent.OrderReceived -> {

                    // εδώ θα γίνει stock increment logic
                    // (WAREHOUSE INBOUND FLOW)

                }

                else -> {}
            }
        }
    }
}