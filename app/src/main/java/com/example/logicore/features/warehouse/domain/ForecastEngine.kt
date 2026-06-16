package com.example.logicore.features.warehouse.domain

import com.example.logicore.features.sales.data.local.SaleLineEntity
import com.example.logicore.features.warehouse.domain.model.ProductForecast

class ForecastEngine {

    private val DAYS_WINDOW = 30.0

    fun forecast(
        lines: List<SaleLineEntity>
    ): List<ProductForecast> {

        val grouped =
            lines.groupBy { it.productId }

        return grouped.map { (productId, sales) ->

            val totalSold =
                sales.sumOf { it.quantity }

            val avgDailyDemand =
                totalSold / DAYS_WINDOW

            ProductForecast(
                productId = productId,
                totalSold = totalSold,
                avgDailyDemand = avgDailyDemand,
                forecast7Days = avgDailyDemand * 7,
                forecast30Days = avgDailyDemand * 30
            )
        }
    }
}