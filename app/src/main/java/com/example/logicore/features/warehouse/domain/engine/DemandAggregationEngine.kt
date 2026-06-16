package com.example.logicore.features.warehouse.domain.engine

import com.example.logicore.features.warehouse.domain.model.DemandEvent
import com.example.logicore.features.warehouse.domain.model.ProductDemand

class DemandAggregationEngine {

    private val DAYS_WINDOW = 7.0

    fun aggregate(
        events: List<DemandEvent>
    ): List<ProductDemand> {

        val grouped =
            events.groupBy { it.productId }

        return grouped.map { (productId, items) ->

            val totalDemand =
                items.sumOf { it.quantity }

            val avg =
                totalDemand / DAYS_WINDOW

            ProductDemand(
                productId = productId,
                dailyAvg = avg,
                trendFactor = 1.0,
                riskFactor = 1.0
            )
        }
    }
}