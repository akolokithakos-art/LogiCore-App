package com.example.logicore.features.events.registry

import com.example.logicore.features.events.data.local.EventStoreEntity
import com.example.logicore.features.events.projections.EventProjection

class ProjectionRegistry(
    private val projections: List<EventProjection>
) {

    fun resolve(event: EventStoreEntity): List<EventProjection> {

        return projections.filter { it.supports(event) }
    }

    fun getByName(name: String): EventProjection? {
        return projections.find { it::class.simpleName == name }
    }
}