package com.example.logicore.features.rl.inference

import org.tensorflow.lite.Interpreter

class DispatchInferenceEngine(
    private val interpreter: Interpreter
) {

    fun predict(input: FloatArray): FloatArray {

        val output = Array(1) { FloatArray(10) }

        interpreter.run(input, output)

        return output[0]
    }
}