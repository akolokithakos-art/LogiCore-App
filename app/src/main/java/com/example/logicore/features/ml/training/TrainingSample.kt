package com.example.logicore.features.ml.training

data class TrainingSample(
    val avgDemand7d: Float,
    val avgDemand30d: Float,
    val stockLevel: Float,
    val volatility: Float,
    val actualDemand: Float
)