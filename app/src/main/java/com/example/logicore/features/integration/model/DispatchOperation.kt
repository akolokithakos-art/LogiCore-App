package com.example.logicore.features.integration.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dispatch_operations")
data class DispatchOperation(

    @PrimaryKey
    val operationId: String,

    val type: String,

    val payload: String,

    val timestamp: Long,

    val synced: Boolean = false
)