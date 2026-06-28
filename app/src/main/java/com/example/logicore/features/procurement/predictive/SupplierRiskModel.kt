package com.example.logicore.features.procurement.predictive

import com.example.logicore.features.stock.projection.StockProjectionEntity

class SupplierRiskModel {

    fun score(projection: StockProjectionEntity): SupplierScore {

        val delayProbability = 0.2 // placeholder

        val reliability = 1.0 - delayProbability

        return SupplierScore(
            reliabilityScore = reliability
        )
    }

}

data class SupplierScore(
    val reliabilityScore: Double
)
