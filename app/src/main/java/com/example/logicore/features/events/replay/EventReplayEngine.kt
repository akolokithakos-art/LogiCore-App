package com.example.logicore.features.events.replay

import com.example.logicore.features.events.checkpoint.CheckpointStore
import com.example.logicore.features.events.data.local.EventStoreDao
import com.example.logicore.features.events.data.local.EventStoreEntity
import com.example.logicore.features.events.registry.ProjectionRegistry
import com.example.logicore.features.events.resilience.ProjectionResult
import com.example.logicore.features.events.resilience.ResilientProjectionExecutor
import com.example.logicore.features.events.resilience.DeadLetterQueue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class EventReplayEngine(
    private val dao: EventStoreDao,
    private val checkpointStore: CheckpointStore,
    private val registry: ProjectionRegistry,
    private val executor: ResilientProjectionExecutor,
    private val dlq: DeadLetterQueue
) {

    companion object {
        private const val BATCH_SIZE = 200
        private const val PROJECTION_NAME = "GLOBAL"
    }

    suspend fun replay(tenantId: String) = withContext(Dispatchers.IO) {

        var offset = checkpointStore.getOffset(
            tenantId,
            PROJECTION_NAME
        )

        while (true) {

            val batch = loadBatch(
                tenantId,
                offset,
                BATCH_SIZE
            )

            if (batch.isEmpty()) break

            processBatch(batch)

            offset += batch.size

            checkpointStore.saveOffset(
                tenantId,
                PROJECTION_NAME,
                offset
            )
        }
    }

    private suspend fun loadBatch(
        tenantId: String,
        offset: Long,
        limit: Int
    ): List<EventStoreEntity> {

        return dao.loadPaged(
            tenantId = tenantId,
            limit = limit,
            offset = offset.toInt()
        )
    }

    private suspend fun processBatch(
        events: List<EventStoreEntity>
    ) {

        for (event in events) {

            val projections = registry.resolve(event)

            for (projection in projections) {

                val result = executor.execute(
                    projection,
                    event
                )

                when (result) {

                    is ProjectionResult.Failed -> {

                        dlq.push(
                            event = event,
                            error = result.error.message ?: "unknown error",
                            projection = projection::class.simpleName ?: "unknown"
                        )
                    }

                    else -> {
                        // success / retry handled inside executor
                    }
                }
            }
        }
    }
}