package com.example.logicore.features.procurement.brain

import com.example.logicore.features.stock.projection.StockProjectionEntity

class ProcurementStateBuilder {

    fun build(projection: StockProjectionEntity): ProcurementState {

        return ProcurementState(
            stockLevel = projection.quantity,
            dailyConsumption = 1.0, // placeholder
            trend = 1.0, // placeholder
            supplierReliability = 0.9, // placeholder
            priceVolatility = 0.05 // placeholder
        )
    }
}

data class ProcurementState(
    val stockLevel: Double,
    val dailyConsumption: Double,
    val trend: Double,
    val supplierReliability: Double,
    val priceVolatility: Double
)
