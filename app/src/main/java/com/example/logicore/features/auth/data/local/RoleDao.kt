package com.example.logicore.features.auth.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RoleDao {

    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    suspend fun upsert(
        role: RoleEntity
    )

    @Query("""
        SELECT *
        FROM roles
        WHERE tenantId = :tenantId
        ORDER BY name ASC
    """)
    suspend fun getRoles(
        tenantId: String
    ): List<RoleEntity>

    @Query("""
        SELECT *
        FROM roles
        WHERE id = :roleId
        LIMIT 1
    """)
    suspend fun findById(
        roleId: Int
    ): RoleEntity?

    @Query("""
        DELETE
        FROM roles
        WHERE id = :roleId
    """)
    suspend fun delete(
        roleId: Int
    )
}