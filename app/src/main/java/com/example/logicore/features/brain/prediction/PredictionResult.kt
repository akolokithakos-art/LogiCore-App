package com.example.logicore.features.brain.prediction

data class PredictionResult(
    val eta: Double,
    val risk: Double,
    val delayProbability: Double
)

class PredictionLayer {

    fun predict(state: Any): PredictionResult {

        // placeholder (later TFLite model)
        return PredictionResult(
            eta = 35.0,
            risk = 0.2,
            delayProbability = 0.1
        )
    }
}