package com.example.logicore.features.dispatch.learning.model

data class DispatchFeedbackEvent(

    val tenantId: String,

    val orderId: Int,
    val vehicleId: Int,

    val assignedScore: Double,

    val delivered: Boolean,

    val deliveryTimeMinutes: Double,

    val expectedTimeMinutes: Double,

    val timestamp: Long = System.currentTimeMillis()
)