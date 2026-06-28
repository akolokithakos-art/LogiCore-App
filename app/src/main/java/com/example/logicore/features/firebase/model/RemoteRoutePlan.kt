package com.example.logicore.features.firebase.model

data class RemoteRoutePlan(
    val tenantId: String,
    val driverId: String,
    val routePoints: List<String>,
    val timestamp: Long = System.currentTimeMillis()
)
