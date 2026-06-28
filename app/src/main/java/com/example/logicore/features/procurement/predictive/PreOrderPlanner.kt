package com.example.logicore.features.procurement.predictive

import com.example.logicore.features.stock.projection.StockProjectionEntity

class PreOrderPlanner {

    fun createPlan(
        projection: StockProjectionEntity,
        forecast: DemandForecast,
        supplierScore: SupplierScore
    ): PreOrderPlan {

        val safetyBuffer = if (supplierScore.reliabilityScore < 0.5) 1.5 else 1.1

        val quantity = (forecast.projectedDemand - projection.quantity) * safetyBuffer

        return PreOrderPlan(
            tenantId = projection.tenantId,
            productId = projection.productId.toString(),
            suggestedQuantity = quantity
        )
    }

}

data class PreOrderPlan(
    val tenantId: String,
    val productId: String,
    val suggestedQuantity: Double
)
