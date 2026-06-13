package com.example.logicore.features.ai.feedback

class FeedbackAnalyzer {

    fun calculateError(event: SalesOutcomeEvent): Double {

        val demandError =
            event.soldQty - event.plannedQty

        return demandError
    }

    fun isOverstock(event: SalesOutcomeEvent): Boolean {
        return event.unsoldQty > 0
    }

    fun isStockout(event: SalesOutcomeEvent): Boolean {
        return event.stockOutOccurred
    }
}