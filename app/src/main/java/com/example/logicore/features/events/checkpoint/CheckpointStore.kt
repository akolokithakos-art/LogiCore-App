package com.example.logicore.features.events.checkpoint

class CheckpointStore(
    private val dao: CheckpointDao
) {

    suspend fun getOffset(
        tenantId: String,
        projection: String
    ): Long {

        return dao.get(tenantId, projection)?.lastOffset ?: 0L
    }

    suspend fun saveOffset(
        tenantId: String,
        projection: String,
        offset: Long
    ) {

        dao.upsert(
            CheckpointEntity(
                tenantId = tenantId,
                projection = projection,
                lastOffset = offset
            )
        )
    }
}