package com.example.logicore.features.events.adaptive

data class ProjectionHealth(
    val projection: String,
    val failureRate: Double,
    val avgRetryCount: Double,
    val lastUpdated: Long,
    val healthScore: Double // 0..1
)