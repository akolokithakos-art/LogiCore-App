package com.example.logicore.features.ml.model

import com.example.logicore.features.ml.features.MLFeatureVector
class ForecastModel {

    fun predictDemand(feature: MLFeatureVector): Double {

        // baseline heuristic (later TFLite / NN replaces this)
        return (
                feature.avgDemand7d * 0.6 +
                        feature.avgDemand30d * 0.3 +
                        feature.stockVolatility * 0.1
                )
    }
}