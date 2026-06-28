package com.example.logicore.features.procurement.predictive

import com.example.logicore.features.stock.projection.StockProjectionEntity

class DemandForecastModel {

    fun predict(projection: StockProjectionEntity): DemandForecast {

        val baseline = projection.averageConsumptionPerDay
        val trend = projection.trendFactor

        val projected = baseline * trend * 7 // 7-day forecast window

        return DemandForecast(
            projectedDemand = projected
        )
    }

}

data class DemandForecast(
    val projectedDemand: Double
)
