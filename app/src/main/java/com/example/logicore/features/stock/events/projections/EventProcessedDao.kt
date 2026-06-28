package com.example.logicore.features.events.projections

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface EventProcessedDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun markProcessed(entity: ProcessedEventEntity)

    @Query("""
        SELECT COUNT(*) 
        FROM processed_events 
        WHERE eventId = :eventId
    """)
    suspend fun exists(eventId: String): Int
}