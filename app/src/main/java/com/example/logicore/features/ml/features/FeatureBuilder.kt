package com.example.logicore.features.ml.features

import com.example.logicore.features.warehouse.events.store.StoredWarehouseEvent

class FeatureBuilder {

    fun build(events: List<StoredWarehouseEvent>): List<MLFeatureVector> {

        val grouped = events.groupBy { it.type }

        // simplified placeholder feature extraction
        return grouped.mapNotNull { (_, list) ->

            val sample = list.firstOrNull() ?: return@mapNotNull null

            MLFeatureVector(
                productId = 0,
                locationId = 0,
                avgDemand7d = list.size.toDouble(),
                avgDemand30d = list.size.toDouble() * 4,
                stockVolatility = list.size * 0.1,
                reorderFrequency = list.size * 0.05
            )
        }
    }
}