package com.example.logicore.features.ml.features

data class MLFeatureVector(
    val productId: Int,
    val locationId: Int,
    val avgDemand7d: Double,
    val avgDemand30d: Double,
    val stockVolatility: Double,
    val reorderFrequency: Double
)