package com.example.logicore.features.ml.training

class ModelTrainer {

    fun train(dataset: List<TrainingSample>): Float {

        if (dataset.isEmpty()) return 0f

        // simplified training metric simulation
        val loss = dataset
            .map { kotlin.math.abs(it.actualDemand - 10f) }
            .average()

        return loss.toFloat()
    }
}