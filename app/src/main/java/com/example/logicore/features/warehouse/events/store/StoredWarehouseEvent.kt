package com.example.logicore.features.warehouse.events.store

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "warehouse_events")
data class StoredWarehouseEvent(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val tenantId: String,

    val type: String,

    val payload: String,

    val createdAt: Long = System.currentTimeMillis()
)