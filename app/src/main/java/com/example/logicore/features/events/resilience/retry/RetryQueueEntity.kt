package com.example.logicore.features.events.resilience.retry

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "retry_queue")
data class RetryQueueEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val eventId: String,

    val tenantId: String,

    val projection: String,

    val payload: String,

    val attempt: Int,

    val nextRetryAt: Long,

    val lastError: String?
)