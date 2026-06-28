package com.example.logicore.features.events.resilience

import com.example.logicore.features.events.data.local.EventStoreEntity

class DeadLetterQueueImpl(
    private val dao: DeadLetterDao
) : DeadLetterQueue {

    override suspend fun push(
        event: EventStoreEntity,
        error: String,
        projection: String
    ) {

        dao.insert(
            DeadLetterEntity(
                tenantId = event.tenantId,
                sourceEventId = event.id,
                aggregateType = event.aggregateType,
                aggregateId = event.aggregateId,
                eventType = event.eventType,
                payload = event.payload,
                projection = projection,
                error = error
            )
        )
    }
}