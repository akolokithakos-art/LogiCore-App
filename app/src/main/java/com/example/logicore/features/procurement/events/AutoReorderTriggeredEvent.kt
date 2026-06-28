package com.example.logicore.features.procurement.events

data class AutoReorderTriggeredEvent(

    val tenantId: String,

    val supplierId: Long,

    val productId: Long,

    val quantity: Double,

    val timestamp: Long =
        System.currentTimeMillis()
)