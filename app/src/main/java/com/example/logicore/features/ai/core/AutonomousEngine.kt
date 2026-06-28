package com.example.logicore.features.ai.core

import com.example.logicore.features.ml.core.MlEngine
import com.example.logicore.features.ai.decision.DecisionEngine
import com.example.logicore.features.stock.domain.StockEngine

class AutonomousEngine(
    private val ml: MlEngine,
    private val decision: DecisionEngine,
    private val stock: StockEngine
) {

    suspend fun run(tenantId: String) {

        val forecasts = ml.runForecast(tenantId)

        forecasts.forEach { (productId, forecast) ->

            val stockLevel = stock.getStock(productId, locationId = 1)

            decision.evaluate(
                productId = productId,
                forecast = forecast,
                stock = stockLevel
            )
        }
    }
}