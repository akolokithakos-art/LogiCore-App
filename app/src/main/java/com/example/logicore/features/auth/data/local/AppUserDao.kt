package com.example.logicore.features.auth.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AppUserDao {

    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    suspend fun upsert(
        user: AppUserEntity
    )

    @Query("""
        SELECT *
        FROM app_users
        WHERE tenantId = :tenantId
        ORDER BY username ASC
    """)
    suspend fun getUsers(
        tenantId: String
    ): List<AppUserEntity>

    @Query("""
        SELECT *
        FROM app_users
        WHERE tenantId = :tenantId
          AND username = :username
        LIMIT 1
    """)
    suspend fun findByUsername(
        tenantId: String,
        username: String
    ): AppUserEntity?

    @Query("""
        SELECT *
        FROM app_users
        WHERE id = :userId
        LIMIT 1
    """)
    suspend fun findById(
        userId: Long
    ): AppUserEntity?

    @Query("""
        DELETE
        FROM app_users
        WHERE id = :userId
    """)
    suspend fun delete(
        userId: Long
    )
}