package com.example.logicore.features.warehouse.domain.engine

import com.example.logicore.features.warehouse.domain.model.PurchaseOrder

class WarehouseIntelligenceEngine(
    private val demandRepo: WarehouseDemandRepository,
    private val stockRepo: WarehouseStockRepository
) {

    fun generateOrders(): List<PurchaseOrder> {

        val demands = demandRepo.getAggregatedDemand()

        val orders = mutableListOf<PurchaseOrder>()

        for (d in demands) {

            val stock = stockRepo.getStock(d.productId)

            val projectedNeed =
                d.dailyAvg * 7 * d.trendFactor * d.riskFactor

            val deficit = projectedNeed - stock.available

            if (deficit > stock.safetyStock) {

                val priority = when {
                    deficit > stock.safetyStock * 3 -> "CRITICAL"
                    deficit > stock.safetyStock * 2 -> "HIGH"
                    else -> "MEDIUM"
                }

                orders.add(
                    PurchaseOrder(
                        productId = d.productId,
                        quantity = deficit,
                        priority = priority,
                        reason = "Demand + forecast + stock gap"
                    )
                )
            }
        }

        return orders
    }
}