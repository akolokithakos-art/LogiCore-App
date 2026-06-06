package com.example.logicore.features.stock.domain

import com.example.logicore.features.stock.data.StockRepository
import com.example.logicore.features.stock.domain.model.StockAlert

class StockAlertEngine(
    private val repo: StockRepository
) {

    private val MIN_STOCK = 5.0
    private val TARGET_STOCK = 20.0

    suspend fun generateAlerts(vanId: Int): List<StockAlert> {

        val movements = repo.getAllMovements()

        val stockMap = mutableMapOf<Int, Double>()

        movements.forEach { m ->

            if (m.fromLocationId == vanId) {
                stockMap[m.productId] = (stockMap[m.productId] ?: 0.0) - m.quantity
            }

            if (m.toLocationId == vanId) {
                stockMap[m.productId] = (stockMap[m.productId] ?: 0.0) + m.quantity
            }
        }

        return stockMap.map { (productId, qty) ->

            val severity = when {
                qty <= 0 -> "CRITICAL"
                qty < MIN_STOCK -> "LOW"
                else -> return@map null
            }

            val recommended = (TARGET_STOCK - qty).coerceAtLeast(0.0)

            StockAlert(
                productId = productId,
                productName = "Product $productId",
                currentStock = qty,
                recommendedQty = recommended,
                severity = severity
            )

        }.filterNotNull()
    }
}