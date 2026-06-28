package com.example.logicore.features.fleet.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DriverDao {

    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    suspend fun upsert(
        driver: DriverEntity
    )

    @Query("""
        SELECT *
        FROM drivers
        WHERE tenantId = :tenantId
        ORDER BY lastName ASC
    """)
    suspend fun getAll(
        tenantId: String
    ): List<DriverEntity>

    @Query("""
        SELECT *
        FROM drivers
        WHERE id = :driverId
        LIMIT 1
    """)
    suspend fun findById(
        driverId: String
    ): DriverEntity?
}