package com.example.logicore.features.stock.domain

import com.example.logicore.features.stock.data.StockRepository
import com.example.logicore.features.stock.domain.model.LoadSuggestion
import kotlin.math.max

class AutoLoadOptimizer(
    private val repo: StockRepository,
    private val stockEngine: StockEngine
) {

    private val DAYS_WINDOW = 7
    private val SAFETY_BUFFER = 1.3 // +30%

    suspend fun generatePlan(vehicleId: Int): List<LoadSuggestion> {

        val sales = repo.getSalesMovementsByVehicle(vehicleId)

        // group sales by product
        val grouped = sales.groupBy { it.productId }

        return grouped.map { (productId, movements) ->

            val totalSold = movements.sumOf { it.quantity }
            val avgDaily = totalSold / DAYS_WINDOW

            val currentStock = stockEngine.getStock(productId, vehicleId)

            val targetStock = avgDaily * DAYS_WINDOW * SAFETY_BUFFER

            val suggested = max(targetStock - currentStock, 0.0)

            LoadSuggestion(
                productId = productId,
                currentStock = currentStock,
                avgDailyDemand = avgDaily,
                suggestedQty = suggested
            )
        }
    }
}