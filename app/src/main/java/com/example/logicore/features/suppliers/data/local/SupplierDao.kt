package com.example.logicore.features.suppliers.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SupplierDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(
        supplier: SupplierEntity
    )

    @Query("""
        SELECT *
        FROM suppliers
        WHERE tenantId = :tenantId
    """)
    suspend fun getAll(
        tenantId: String
    ): List<SupplierEntity>
}