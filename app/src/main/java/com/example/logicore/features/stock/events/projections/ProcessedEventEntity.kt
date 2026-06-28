package com.example.logicore.features.events.projections

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "processed_events")
data class ProcessedEventEntity(

    @PrimaryKey
    val eventId: String,

    val processedAt: Long = System.currentTimeMillis()
)