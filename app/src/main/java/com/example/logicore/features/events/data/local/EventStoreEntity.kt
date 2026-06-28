package com.example.logicore.features.events.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "event_store")
data class EventStoreEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val tenantId: String,

    val aggregateType: String,
    // STOCK / PROCUREMENT / SALES / WAREHOUSE

    val aggregateId: String?,   // orderId, productId, etc.

    val eventType: String,

    val payload: String, // JSON

    val timestamp: Long = System.currentTimeMillis()
)