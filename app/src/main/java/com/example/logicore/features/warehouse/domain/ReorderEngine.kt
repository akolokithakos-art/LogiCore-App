package com.example.logicore.features.warehouse.domain

import com.example.logicore.features.stock.domain.StockEngine
import com.example.logicore.features.warehouse.domain.model.ProductForecast
import com.example.logicore.features.warehouse.domain.model.ReorderSuggestion
import kotlin.math.max

class ReorderEngine(
    private val stockEngine: StockEngine
) {

    suspend fun generateSuggestions(
        warehouseLocationId: Int,
        forecasts: List<ProductForecast>
    ): List<ReorderSuggestion> {

        return forecasts.mapNotNull { forecast ->

            val stock =
                stockEngine.getStock(
                    productId = forecast.productId,
                    locationId = warehouseLocationId
                )

            val deficit =
                forecast.forecast30Days - stock

            if (deficit <= 0)
                return@mapNotNull null

            ReorderSuggestion(
                productId = forecast.productId,
                currentStock = stock,
                forecast30Days = forecast.forecast30Days,
                suggestedOrderQty = max(deficit, 0.0)
            )
        }
    }
}