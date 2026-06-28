package com.example.logicore.features.ml.learning

import kotlin.math.abs

class RewardCalculator {

    fun calculate(predicted: Double, actual: Double): Double {

        val error = abs(predicted - actual)

        return when {
            error < 1 -> 1.0
            error < 5 -> 0.5
            error < 10 -> 0.2
            else -> -1.0
        }
    }
}