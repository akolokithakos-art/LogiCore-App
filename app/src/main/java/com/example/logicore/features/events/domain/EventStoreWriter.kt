package com.example.logicore.features.events.domain

import com.example.logicore.features.core.events.EventBus
import com.example.logicore.features.core.events.HasAggregate
import com.example.logicore.features.core.events.HasTenant
import com.example.logicore.features.events.data.local.EventStoreDao
import com.example.logicore.features.events.data.local.EventStoreEntity
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class EventStoreWriter(
    private val dao: EventStoreDao,
    private val eventBus: EventBus,
    private val gson: Gson
) {

    fun attach(scope: CoroutineScope) {

        scope.launch {

            eventBus.events.collect { event ->

                val entity = EventStoreEntity(
                    tenantId = extractTenantId(event),
                    aggregateType = event::class.simpleName ?: "UNKNOWN",
                    aggregateId = extractAggregateId(event),
                    eventType = event::class.simpleName ?: "UNKNOWN",
                    payload = gson.toJson(event)
                )

                dao.append(entity)
            }
        }
    }

    private fun extractTenantId(event: Any): String {
        return when (event) {
            is HasTenant -> event.tenantId
            else -> "SYSTEM"
        }
    }

    private fun extractAggregateId(event: Any): String? {
        return when (event) {
            is HasAggregate -> event.aggregateId
            else -> null
        }
    }
}