package com.example.logicore.features.events.resilience

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DeadLetterDao {

    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    suspend fun insert(
        entity: DeadLetterEntity
    )

    @Query("""
        SELECT *
        FROM dead_letter_queue
        WHERE tenantId = :tenantId
        ORDER BY createdAt ASC
    """)
    suspend fun loadAll(
        tenantId: String
    ): List<DeadLetterEntity>

    @Query("""
        UPDATE dead_letter_queue
        SET replayed = 1
        WHERE id = :id
    """)
    suspend fun markReplayed(
        id: Long
    )

    @Query("""
        DELETE
        FROM dead_letter_queue
        WHERE id = :id
    """)
    suspend fun delete(
        id: Long
    )
}