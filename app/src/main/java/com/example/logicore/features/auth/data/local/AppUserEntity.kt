package com.example.logicore.features.auth.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "app_users"
)
data class AppUserEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val tenantId: String,

    val username: String,

    val passwordHash: String,

    val roleId: Int,

    val firstName: String,

    val lastName: String,

    val email: String?,

    val phone: String?,

    val active: Boolean = true,

    val createdAt: Long =
        System.currentTimeMillis()
)