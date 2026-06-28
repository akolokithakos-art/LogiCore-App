package com.example.logicore.features.ml.core

import com.example.logicore.features.ml.tflite.*

class TFLiteForecastEngine(
    private val inferenceEngine: TFLiteInferenceEngine
) {

    fun predict(input: ModelInput): Float {
        return inferenceEngine
            .predict(input)
            .predictedDemand
    }
}