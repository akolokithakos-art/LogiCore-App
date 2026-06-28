package com.example.logicore.features.fleet.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DriverVehicleAssignmentDao {

    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    suspend fun insert(
        assignment: DriverVehicleAssignmentEntity
    )

    @Query("""
        SELECT *
        FROM driver_vehicle_assignments
        WHERE tenantId = :tenantId
          AND driverId = :driverId
          AND active = 1
        LIMIT 1
    """)
    suspend fun findActiveVehicleForDriver(
        tenantId: String,
        driverId: String
    ): DriverVehicleAssignmentEntity?

    @Query("""
        SELECT *
        FROM driver_vehicle_assignments
        WHERE tenantId = :tenantId
          AND vehicleId = :vehicleId
          AND active = 1
        LIMIT 1
    """)
    suspend fun findActiveDriverForVehicle(
        tenantId: String,
        vehicleId: String
    ): DriverVehicleAssignmentEntity?

    @Query("""
        UPDATE driver_vehicle_assignments
        SET active = 0
        WHERE id = :assignmentId
    """)
    suspend fun deactivate(
        assignmentId: Long
    )
}