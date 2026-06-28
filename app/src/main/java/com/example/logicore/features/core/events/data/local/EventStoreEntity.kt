package com.example.logicore.features.core.events.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "event_store")
data class EventStoreEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val tenantId: String,
    val aggregateType: String,
    val aggregateId: String?,
    val eventType: String,
    val payload: String,
    val timestamp: Long = System.currentTimeMillis()
)