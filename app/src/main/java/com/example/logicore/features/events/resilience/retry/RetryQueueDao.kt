package com.example.logicore.features.events.resilience.retry

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RetryQueueDao {

    @Insert
    suspend fun insert(item: RetryQueueEntity)

    @Query("""
        SELECT *
        FROM retry_queue
        WHERE nextRetryAt <= :now
        ORDER BY nextRetryAt ASC
        LIMIT :limit
    """)
    suspend fun fetchDue(now: Long, limit: Int): List<RetryQueueEntity>

    @Query("""
        DELETE FROM retry_queue
        WHERE id = :id
    """)
    suspend fun delete(id: Long)
}