package com.example.logicore.features.ml.learning

class AutoTrainer(
    private val store: PredictionStore
) {

    suspend fun shouldRetrain(): Boolean {

        val error = store.getAll()
            .filter { it.actual != null }
            .map { kotlin.math.abs(it.predicted - it.actual!!) }
            .average()

        return error > 5.0
    }
}