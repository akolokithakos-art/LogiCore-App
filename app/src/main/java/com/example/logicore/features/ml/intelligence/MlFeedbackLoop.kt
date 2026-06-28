package com.example.logicore.features.ml.intelligence

import com.example.logicore.features.stock.intelligence.StockIntelligenceEngine

class MlFeedbackLoop(
    private val intelligence: StockIntelligenceEngine
) {

    suspend fun onPrediction(
        productId: Int,
        predicted: Double,
        actual: Double
    ) {

        intelligence.analyze(
            productId = productId,
            expected = predicted,
            actual = actual
        )
    }
}