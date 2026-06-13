package com.example.logicore.features.ai.route

class RouteDemandEngine {

    fun calculateDemand(signals: List<RouteDemandSignal>): Map<Int, Double> {

        val result = mutableMapOf<Int, Double>()

        for (signal in signals) {

            val base =
                signal.predictedQty * 0.5 +
                        signal.avgSalesPerStop * signal.customerCount * 0.3 +
                        (1.0 / (signal.lastRouteVisitDaysAgo + 1)) * 10 * 0.2

            result[signal.productId] =
                (result[signal.productId] ?: 0.0) + base
        }

        return result
    }
}