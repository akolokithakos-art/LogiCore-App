package com.example.logicore.features.tenant.data.local

import androidx.room.Dao
import androidx.room.Query

@Dao
interface TenantDao {
    @Query("SELECT * FROM tenants")
    suspend fun getAllTenants(): List<Tenant>
}
