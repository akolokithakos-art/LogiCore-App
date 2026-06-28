package com.example.logicore.features.events.domain

import com.example.logicore.features.events.data.local.EventStoreDao
import com.example.logicore.features.events.data.local.EventStoreEntity
import kotlinx.coroutines.flow.first

class MLDatasetBuilder(
    private val dao: EventStoreDao
) {

    suspend fun build(tenantId: String): List<EventStoreEntity> {

        val events = dao.streamAll(tenantId).first()

        // εδώ θα γίνει feature extraction pipeline αργότερα
        return events
    }
}