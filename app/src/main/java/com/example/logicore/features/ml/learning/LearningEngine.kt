package com.example.logicore.features.ml.learning

import com.example.logicore.features.ml.core.MlEngine

class LearningEngine(
    private val store: PredictionStore,
    private val evaluator: PredictionEvaluator
) {

    suspend fun recordPrediction(productId: Int, predicted: Double) {

        store.save(
            PredictionRecord(
                productId = productId,
                predicted = predicted,
                actual = null
            )
        )
    }

    suspend fun feedActual(productId: Int, actual: Double) {

        store.updateActual(productId, actual)
    }

    suspend fun evaluate(): Double {

        val records = store.getAll()
        return evaluator.evaluate(records)
    }
}