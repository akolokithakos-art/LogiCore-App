package com.example.logicore.features.ai.feedback

class LearningEngine {

    private val adjustments = mutableMapOf<Int, Double>()

    fun applyFeedback(event: SalesOutcomeEvent) {

        val error =
            event.soldQty - event.plannedQty

        val current =
            adjustments[event.productId] ?: 1.0

        val updated =
            current + (error * 0.1)

        adjustments[event.productId] = updated
    }

    fun getAdjustment(productId: Int): Double {
        return adjustments[productId] ?: 1.0
    }
}