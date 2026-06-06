package com.example.logicore.features.dispatch.domain

import com.example.logicore.features.ai.domain.RouteDemandAI
import com.example.logicore.features.dispatch.domain.model.DispatchPlan
import com.example.logicore.features.stock.domain.StockEngine
import kotlin.math.max

class PredictiveDispatchEngine(
    private val demandAI: RouteDemandAI,
    private val stockEngine: StockEngine
) {

    suspend fun generate(vehicleId: Int): List<DispatchPlan> {

        val demand = demandAI.analyze(vehicleId)

        return demand.map { d ->

            val currentStock = stockEngine.getStock(
                productId = d.productId,
                locationId = vehicleId
            )

            val deficit = d.recommendedLoad - currentStock

            val priority = when {
                deficit <= 0 -> "LOW"
                deficit < d.recommendedLoad * 0.3 -> "MEDIUM"
                else -> "HIGH"
            }

            DispatchPlan(
                vehicleId = vehicleId,
                productId = d.productId,
                zoneId = d.zoneId,
                recommendedLoad = max(deficit, 0.0),
                priority = priority
            )
        }.filter { it.recommendedLoad > 0 }
    }
}