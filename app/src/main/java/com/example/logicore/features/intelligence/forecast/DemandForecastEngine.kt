package com.example.logicore.features.intelligence.forecast

import com.example.logicore.features.ai.domain.model.ZoneDemand
import kotlin.math.max

/**
 * Forecast future demand based on simple trend smoothing
 * (production-safe baseline, replace later with ML model)
 */
class DemandForecastEngine {

    fun forecast(demandHistory: List<ZoneDemand>): List<ZoneDemandForecast> {

        if (demandHistory.isEmpty()) return emptyList()

        val grouped = demandHistory.groupBy { it.productId }

        return grouped.map { (productId, list) ->

            val avg = list.map { it.recommendedLoad }.average()

            val trend = if (list.size >= 2) {
                val last = list.last().recommendedLoad
                val prev = list.first().recommendedLoad
                (last - prev) / (list.size.coerceAtLeast(1))
            } else 0.0

            val predicted = max(avg + trend, 0.0)

            ZoneDemandForecast(
                productId = productId,
                predictedDemand = predicted,
                confidence = calculateConfidence(list.size)
            )
        }
    }

    private fun calculateConfidence(samples: Int): Double {
        return when {
            samples >= 20 -> 0.9
            samples >= 10 -> 0.7
            samples >= 5 -> 0.5
            else -> 0.3
        }
    }
}