package com.example.logicore.features.warehouse.events.store

import com.example.logicore.features.warehouse.events.model.WarehouseEvent
import com.example.logicore.features.tenant.core.TenantContext

class WarehouseEventStore(
    private val dao: WarehouseEventDao,
    private val serializer: EventSerializer,
    private val tenantContext: TenantContext
) {

    private fun tenant(): String =
        tenantContext.getTenant()
            ?: throw IllegalStateException("No tenant selected")

    suspend fun append(event: WarehouseEvent) {

        dao.append(
            StoredWarehouseEvent(
                tenantId = tenant(),
                type = event::class.simpleName ?: "Unknown",
                payload = serializer.toJson(event)
            )
        )
    }
}