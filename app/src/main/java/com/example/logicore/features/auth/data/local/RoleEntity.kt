package com.example.logicore.features.auth.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "roles"
)
data class RoleEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val tenantId: String,

    val name: String,

    val description: String? = null
)