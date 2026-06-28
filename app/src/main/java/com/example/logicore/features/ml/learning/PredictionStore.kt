package com.example.logicore.features.ml.learning

import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class PredictionStore {

    private val mutex = Mutex()
    private val predictions = mutableListOf<PredictionRecord>()

    suspend fun save(record: PredictionRecord) {
        mutex.withLock {
            predictions.add(record)
        }
    }

    suspend fun getAll(): List<PredictionRecord> {
        return mutex.withLock { predictions.toList() }
    }

    suspend fun updateActual(productId: Int, actual: Double) {
        mutex.withLock {
            predictions.replaceAll {
                if (it.productId == productId && it.actual == null) {
                    it.copy(actual = actual)
                } else it
            }
        }
    }
}