package com.example.logicore.features.ai.domain

import com.example.logicore.features.ai.domain.model.ZoneDemand
import com.example.logicore.features.stock.data.StockRepository
import com.example.logicore.features.stock.data.local.StockMovementEntity
import kotlin.math.max

class RouteDemandAI(
    private val repo: StockRepository
) {

    private val DAYS_WINDOW = 7
    private val SAFETY_MULTIPLIER = 1.25

    suspend fun analyze(vehicleId: Int): List<ZoneDemand> {

        val movements = repo.getAllMovements()

        val sales = movements.filter {
            it.type == "SALE" && it.fromLocationId == vehicleId
        }

        // group by zone + product
        val grouped = sales.groupBy { it.zoneId to it.productId }

        return grouped.mapNotNull { (key, list) ->

            val zoneId = key.first ?: return@mapNotNull null
            val productId = key.second

            val total = list.sumOf { it.quantity }
            val avg = total / DAYS_WINDOW

            val recommended = avg * DAYS_WINDOW * SAFETY_MULTIPLIER

            ZoneDemand(
                productId = productId,
                zoneId = zoneId,
                avgDemand = avg,
                recommendedLoad = recommended
            )
        }
    }
}