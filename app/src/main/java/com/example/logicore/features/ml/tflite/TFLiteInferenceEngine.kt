package com.example.logicore.features.ml.tflite

import org.tensorflow.lite.Interpreter

class TFLiteInferenceEngine(
    private val interpreter: Interpreter
) {

    fun predict(input: ModelInput): ModelOutput {

        val inputArray = arrayOf(
            floatArrayOf(
                input.avgDemand7d,
                input.avgDemand30d,
                input.stockLevel,
                input.volatility
            )
        )

        val output = Array(1) { FloatArray(1) }

        interpreter.run(inputArray, output)

        return ModelOutput(
            predictedDemand = output[0][0]
        )
    }
}