package com.example.logicore.features.events.resilience

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "dead_letter_queue"
)
data class DeadLetterEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val tenantId: String,

    val sourceEventId: Long,

    val aggregateType: String,

    val aggregateId: String?,

    val eventType: String,

    val payload: String,

    val projection: String,

    val error: String,

    val createdAt: Long =
        System.currentTimeMillis(),

    val replayed: Boolean = false
)