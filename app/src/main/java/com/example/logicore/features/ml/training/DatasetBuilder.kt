package com.example.logicore.features.ml.training

import com.example.logicore.features.ml.learning.PredictionStore

class DatasetBuilder(
    private val store: PredictionStore
) {

    suspend fun build(): List<TrainingSample> {

        return store.getAll()
            .filter { it.actual != null }
            .map {

                TrainingSample(
                    avgDemand7d = 0.0f,   // placeholder → θα δεθεί με features DB
                    avgDemand30d = 0.0f,
                    stockLevel = 0.0f,
                    volatility = 0.0f,
                    actualDemand = it.actual!!.toFloat()
                )
            }
    }
}