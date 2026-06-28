package com.example.logicore.features.events.bootstrap

import com.example.logicore.features.events.replay.EventReplayEngine
import com.example.logicore.features.tenant.data.local.TenantDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProjectionBootstrap(
    private val tenantDao: TenantDao,
    private val replayEngine: EventReplayEngine
) {

    /**
     * Rebuilds ALL projections from EventStore (full replay)
     * Used on app startup or recovery
     */
    suspend fun rebuildAllTenants() = withContext(Dispatchers.IO) {

        val tenants = tenantDao.getAllTenants()

        for (tenant in tenants) {

            try {
                replayEngine.replay(tenant.id)
            } catch (e: Exception) {
                // production-safe: do not crash app
                e.printStackTrace()
            }
        }
    }
}