package com.example.logicore.features.ml.core

import com.example.logicore.features.core.resilience.CircuitBreaker
import com.example.logicore.features.ml.tflite.ModelInput
import com.example.logicore.features.ml.tflite.ModelOutput
import com.example.logicore.features.ml.tflite.TFLiteInferenceEngine

class MlSafeEngine(
    private val engine: TFLiteInferenceEngine,
    private val breaker: CircuitBreaker
) {

    fun predict(
        input: ModelInput
    ): ModelOutput? {

        if (!breaker.allow()) {
            return null
        }

        return try {

            val result = engine.predict(input)

            breaker.recordSuccess()

            result

        } catch (e: Exception) {

            breaker.recordFailure()

            null
        }
    }
}