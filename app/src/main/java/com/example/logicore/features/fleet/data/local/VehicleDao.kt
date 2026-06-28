package com.example.logicore.features.fleet.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface VehicleDao {

    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    suspend fun upsert(
        vehicle: VehicleEntity
    )

    @Query("""
        SELECT *
        FROM vehicles
        WHERE tenantId = :tenantId
        ORDER BY code ASC
    """)
    suspend fun getAll(
        tenantId: String
    ): List<VehicleEntity>

    @Query("""
        SELECT *
        FROM vehicles
        WHERE id = :vehicleId
        LIMIT 1
    """)
    suspend fun findById(
        vehicleId: String
    ): VehicleEntity?
}