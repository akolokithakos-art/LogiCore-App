package com.example.logicore.features.stock.intelligence

class StockVelocityAnalyzer {

    fun classify(dailyDemand: Double): String {

        return when {
            dailyDemand > 10 -> "FAST"
            dailyDemand < 2 -> "SLOW"
            else -> "NORMAL"
        }
    }
}