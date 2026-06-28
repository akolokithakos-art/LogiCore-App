package com.example.logicore.features.events.checkpoint

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CheckpointDao {

    @Query("""
        SELECT * 
        FROM event_checkpoints
        WHERE tenantId = :tenantId
          AND projection = :projection
        LIMIT 1
    """)
    suspend fun get(
        tenantId: String,
        projection: String
    ): CheckpointEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(
        checkpoint: CheckpointEntity
    )
}