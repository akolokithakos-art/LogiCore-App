package com.example.logicore.features.events.data.local

import com.example.logicore.features.core.events.EventBus
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect

class EventStoreWriter(
    private val dao: EventStoreDao,
    private val bus: EventBus
) {

    private val gson = Gson()

    fun start(tenantId: String) {

        kotlinx.coroutines.GlobalScope.launch {

            bus.events.collect { event ->

                dao.append(
                    EventStoreEntity(
                        tenantId = tenantId,
                        aggregateType = event::class.simpleName ?: "unknown",
                        aggregateId = null,
                        eventType = event::class.simpleName ?: "unknown",
                        payload = gson.toJson(event)
                    )
                )
            }
        }
    }
}