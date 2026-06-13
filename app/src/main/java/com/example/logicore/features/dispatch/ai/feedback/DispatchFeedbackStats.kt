package com.example.logicore.features.dispatch.ai.feedback.model

data class DispatchFeedbackStats(
    val vehicleId: Int,
    val successRate: Double,
    val avgDeliveryTimeScore: Double,
    val rejectionRate: Double
)