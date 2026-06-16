package com.example.logicore.features.intelligence.forecast

import kotlin.math.max

/**
 * Predicts days until stock runs out
 */
class StockDepletionPredictor {

    fun predictDaysToStockout(
        currentStock: Double,
        dailyDemand: Double
    ): Double {

        if (dailyDemand <= 0) return Double.POSITIVE_INFINITY

        return currentStock / dailyDemand
    }

    fun isCritical(currentStock: Double, dailyDemand: Double): Boolean {
        val days = predictDaysToStockout(currentStock, dailyDemand)
        return days < 2.0
    }
}