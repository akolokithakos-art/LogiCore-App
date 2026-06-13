package com.example.logicore.features.integration.sync

import com.example.logicore.features.integration.local.DispatchQueueDao
import com.example.logicore.features.integration.local.DispatchOperation
import kotlinx.coroutines.flow.collect

class DispatchSyncEngine(
    private val dao: DispatchQueueDao
) {

    suspend fun syncPending() {

        dao.getPending().collect { list ->

            for (op in list) {

                try {
                    pushToServer(op)
                    dao.markSynced(op.operationId)

                } catch (e: Exception) {
                    // keep in queue
                }
            }
        }
    }

    private fun pushToServer(op: DispatchOperation) {
        // Firebase / REST API later
    }
}