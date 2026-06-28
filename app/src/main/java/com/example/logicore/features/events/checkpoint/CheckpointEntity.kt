package com.example.logicore.features.events.checkpoint

import androidx.room.Entity

@Entity(
    tableName = "event_checkpoints",
    primaryKeys = ["tenantId", "projection"]
)
data class CheckpointEntity(

    val tenantId: String,

    val projection: String,

    val lastOffset: Long,

    val updatedAt: Long = System.currentTimeMillis()
)