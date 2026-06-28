package com.example.logicore.features.ml.tflite

data class ModelInput(
    val avgDemand7d: Float,
    val avgDemand30d: Float,
    val stockLevel: Float,
    val volatility: Float
)