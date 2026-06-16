package com.example.logicore.features.warehouse.events.store

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface WarehouseEventDao {

    @Insert
    suspend fun append(event: StoredWarehouseEvent)

    @Query("""
        SELECT *
        FROM warehouse_events
        WHERE tenantId = :tenantId
        ORDER BY id ASC
    """)
    fun streamAll(tenantId: String): Flow<List<StoredWarehouseEvent>>

    @Query("""
        SELECT *
        FROM warehouse_events
        WHERE tenantId = :tenantId
          AND id > :fromId
        ORDER BY id ASC
    """)
    suspend fun replayFrom(
        tenantId: String,
        fromId: Long
    ): List<StoredWarehouseEvent>
}