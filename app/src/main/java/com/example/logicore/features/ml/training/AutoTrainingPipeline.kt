package com.example.logicore.features.ml.training

import com.example.logicore.features.ml.learning.PredictionStore

class AutoTrainingPipeline(
    private val datasetBuilder: DatasetBuilder,
    private val trainer: ModelTrainer,
    private val exporter: ModelExporter,
    private val registry: ModelRegistry,
    private val driftDetector: DriftDetector
) {

    suspend fun run() {

        val dataset = datasetBuilder.build()

        val loss = trainer.train(dataset)

        val currentVersion = registry.getActive()
        val newVersion = "v${System.currentTimeMillis()}"

        val shouldUpgrade = driftDetector.detect(oldError = 5.0, newError = loss.toDouble())

        if (shouldUpgrade) {

            exporter.export(newVersion)
            registry.update(newVersion)
        }
    }
}