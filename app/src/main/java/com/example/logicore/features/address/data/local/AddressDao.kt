package com.example.logicore.features.address.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AddressDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(address: AddressEntity)

    @Query("""
        SELECT *
        FROM addresses
        WHERE tenantId = :tenantId
    """)
    suspend fun getAll(
        tenantId: String
    ): List<AddressEntity>

    @Query("""
        SELECT *
        FROM addresses
        WHERE tenantId = :tenantId
          AND referenceType = :referenceType
          AND referenceId = :referenceId
        LIMIT 1
    """)
    suspend fun getAddress(
        tenantId: String,
        referenceType: String,
        referenceId: Int
    ): AddressEntity?
}