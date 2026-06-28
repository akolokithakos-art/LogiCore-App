package com.example.logicore.features.events.projections

import com.example.logicore.features.events.data.local.EventStoreEntity

interface EventProjection {

    fun supports(event: EventStoreEntity): Boolean

    suspend fun handle(
        event: EventStoreEntity
    )
}