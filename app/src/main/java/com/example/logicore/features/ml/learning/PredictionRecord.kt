package com.example.logicore.features.ml.learning

data class PredictionRecord(
    val productId: Int,
    val predicted: Double,
    val actual: Double?,
    val timestamp: Long = System.currentTimeMillis()
)