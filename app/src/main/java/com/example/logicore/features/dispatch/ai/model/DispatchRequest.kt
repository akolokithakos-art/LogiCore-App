package com.example.logicore.features.dispatch.ai.model

data class DispatchRequest(
    val orderLat: Double,
    val orderLng: Double,
    val requiredCapacity: Double
)