package com.example.logicore.features.events.resilience

import com.example.logicore.features.events.data.local.EventStoreEntity

interface DeadLetterQueue {

    suspend fun push(
        event: EventStoreEntity,
        error: String,
        projection: String
    )
}