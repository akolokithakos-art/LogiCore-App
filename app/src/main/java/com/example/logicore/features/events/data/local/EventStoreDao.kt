package com.example.logicore.features.events.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface EventStoreDao {

    @Insert
    suspend fun append(event: EventStoreEntity)

    @Query("""
        SELECT *
        FROM event_store
        WHERE id = :id
    """)
    suspend fun getById(id: Long): EventStoreEntity?

    @Query("""
        SELECT *
        FROM event_store
        WHERE tenantId = :tenantId
        ORDER BY timestamp ASC
    """)
    fun streamAll(
        tenantId: String
    ): Flow<List<EventStoreEntity>>

    @Query("""
        SELECT *
        FROM event_store
        WHERE tenantId = :tenantId
        ORDER BY timestamp ASC
    """)
    suspend fun loadAll(
        tenantId: String
    ): List<EventStoreEntity>

    @Query("""
    SELECT *
    FROM event_store
    WHERE tenantId = :tenantId
    ORDER BY timestamp ASC
    LIMIT :limit OFFSET :offset
""")
    suspend fun loadPaged(
        tenantId: String,
        limit: Int,
        offset: Int
    ): List<EventStoreEntity>

    @Query("""
        SELECT *
        FROM event_store
        WHERE tenantId = :tenantId
          AND aggregateType = :type
        ORDER BY timestamp ASC
    """)
    suspend fun loadByType(
        tenantId: String,
        type: String
    ): List<EventStoreEntity>
}