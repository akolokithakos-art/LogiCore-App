package com.example.logicore.features.integration.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface DispatchQueueDao {

    @Insert
    suspend fun enqueue(op: DispatchOperation)

    @Query("""
        SELECT * FROM dispatch_operations
        WHERE synced = 0
        ORDER BY timestamp ASC
    """)
    fun getPending(): Flow<List<DispatchOperation>>

    @Query("""
        UPDATE dispatch_operations
        SET synced = 1
        WHERE operationId = :id
    """)
    suspend fun markSynced(id: String)
}