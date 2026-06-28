package com.example.logicore.features.dispatch.integration

import com.example.logicore.features.events.data.local.EventStoreDao
import com.example.logicore.features.events.data.local.EventStoreEntity
import com.example.logicore.features.dispatch.events.DispatchAssignedEvent
import com.google.gson.Gson

class DispatchEventWriter(
    private val dao: EventStoreDao,
    private val gson: Gson
) {

    suspend fun write(event: DispatchAssignedEvent) {

        dao.append(
            EventStoreEntity(
                tenantId = event.tenantId,
                aggregateType = "DISPATCH",
                aggregateId = event.orderId,
                eventType = "DISPATCH_ASSIGNED",
                payload = gson.toJson(event)
            )
        )
    }
}