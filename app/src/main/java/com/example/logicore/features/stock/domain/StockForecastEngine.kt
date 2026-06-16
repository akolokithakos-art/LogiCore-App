package com.example.logicore.features.stock.domain

import com.example.logicore.features.stock.data.StockRepository
import com.example.logicore.features.tenant.core.TenantContext
import kotlin.math.max

class StockForecastEngine(
    private val repo: StockRepository,
    private val tenantContext: TenantContext
) {

    private val DAYS_WINDOW = 7

    private fun tenant(): String =
        tenantContext.getTenant()
            ?: throw IllegalStateException("No tenant")

    suspend fun forecastDailyDemand(vehicleId: Int): Map<Int, Double> {

        val movements = repo.getAllMovements(tenant())

        val sales = movements.filter {
            it.type == "SALE" && it.fromLocationId == vehicleId
        }

        val grouped = sales.groupBy { it.productId }

        return grouped.mapValues { (_, items) ->
            val total = items.sumOf { it.quantity }
            total / DAYS_WINDOW
        }
    }
}