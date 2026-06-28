package com.example.logicore.features.tenant.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TenantDao {

    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    suspend fun upsert(
        tenant: TenantEntity
    )

    @Query("""
        SELECT *
        FROM tenants
        WHERE id = :tenantId
        LIMIT 1
    """)
    suspend fun findById(
        tenantId: String
    ): TenantEntity?

    @Query("""
        SELECT *
        FROM tenants
        ORDER BY companyName ASC
    """)
    suspend fun getAllTenants(): List<TenantEntity>

    @Query("""
        DELETE
        FROM tenants
        WHERE id = :tenantId
    """)
    suspend fun delete(
        tenantId: String
    )
}