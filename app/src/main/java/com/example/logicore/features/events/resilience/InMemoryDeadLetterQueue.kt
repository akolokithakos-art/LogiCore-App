package com.example.logicore.features.events.resilience

import com.example.logicore.features.events.data.local.EventStoreEntity

class InMemoryDeadLetterQueue : DeadLetterQueue {

    private val failedEvents = mutableListOf<EventStoreEntity>()

    override suspend fun push(
        event: EventStoreEntity,
        error: String,
        projection: String
    ) {
        failedEvents.add(event)
    }
}