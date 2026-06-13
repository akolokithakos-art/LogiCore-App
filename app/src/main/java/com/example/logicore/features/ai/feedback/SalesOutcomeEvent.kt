package com.example.logicore.features.ai.feedback

data class SalesOutcomeEvent(

    val routeId: Int,
    val productId: Int,

    val plannedQty: Double,
    val soldQty: Double,

    val unsoldQty: Double,

    val stockOutOccurred: Boolean,

    val timestamp: Long = System.currentTimeMillis()
)