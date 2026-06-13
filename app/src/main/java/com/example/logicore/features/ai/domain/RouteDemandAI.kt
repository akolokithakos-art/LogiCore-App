package com.example.logicore.features.ai.domain

import com.example.logicore.features.ai.domain.model.ZoneDemand
import com.example.logicore.features.stock.data.StockRepository
import com.example.logicore.features.tenant.core.TenantContext

class RouteDemandAI(
    private val repo: StockRepository,
    private val tenantContext: TenantContext
) {

    private val DAYS_WINDOW = 7
    private val SAFETY_MULTIPLIER = 1.25

    private fun tenant(): String =
        tenantContext.getTenant()
            ?: throw IllegalStateException("No tenant selected")

    suspend fun analyze(vehicleId: Int): List<ZoneDemand> {

        val movements = repo.getAllMovements(
            tenantId = tenant()
        )

        val sales = movements.filter {
            it.type == "SALE" && it.fromLocationId == vehicleId
        }

        val grouped = sales.groupBy { it.productId }

        return grouped.map { (productId, list) ->

            val total = list.sumOf { it.quantity }
            val avg = total / DAYS_WINDOW

            val recommended = avg * DAYS_WINDOW * SAFETY_MULTIPLIER

            ZoneDemand(
                productId = productId,
                zoneId = vehicleId,
                avgDemand = avg,
                recommendedLoad = recommended
            )
        }
    }
}