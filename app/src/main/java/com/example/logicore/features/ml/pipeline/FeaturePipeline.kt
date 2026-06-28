package com.example.logicore.features.ml.pipeline

import com.example.logicore.features.events.data.local.EventStoreEntity
import com.example.logicore.features.ml.features.MLFeatureVector
import kotlin.math.max

class FeaturePipeline {

    fun build(events: List<EventStoreEntity>): List<MLFeatureVector> {

        val grouped = events.groupBy { it.aggregateId ?: "unknown" }

        return grouped.map { (productIdRaw, list) ->

            val productId = productIdRaw.toIntOrNull() ?: 0

            val salesEvents = list.filter {
                it.eventType.contains("SALE", true)
            }

            val demand7d = salesEvents.takeLast(7).size.toDouble()
            val demand30d = salesEvents.takeLast(30).size.toDouble()

            val volatility = calculateVolatility(salesEvents.size.toDouble())

            MLFeatureVector(
                productId = productId,
                locationId = 0,
                avgDemand7d = demand7d / 7.0,
                avgDemand30d = demand30d / 30.0,
                stockVolatility = volatility,
                reorderFrequency = salesEvents.size.toDouble()
            )
        }
    }

    private fun calculateVolatility(value: Double): Double {
        return max(0.0, value / 100.0)
    }
}