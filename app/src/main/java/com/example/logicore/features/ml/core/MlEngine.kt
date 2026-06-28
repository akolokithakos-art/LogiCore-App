package com.example.logicore.features.ml.core

import com.example.logicore.features.ml.features.FeatureBuilder
import com.example.logicore.features.ml.tflite.ModelInput
import com.example.logicore.features.ml.tflite.TFLiteInferenceEngine
import com.example.logicore.features.warehouse.events.store.WarehouseEventDao
import com.example.logicore.features.events.data.local.EventStoreDao
import com.example.logicore.features.warehouse.events.WarehouseEventBus
import com.example.logicore.features.stock.domain.StockEngine
import com.example.logicore.features.warehouse.events.model.WarehouseEvent
import kotlinx.coroutines.flow.first

class MlEngine(
    private val dao: WarehouseEventDao,
    private val featureBuilder: FeatureBuilder,
    private val tfliteEngine: TFLiteInferenceEngine,
    private val bus: WarehouseEventBus,
    private val stockEngine: StockEngine
) {

    suspend fun runForecast(tenantId: String): Map<Int, Double> {

        val events = dao.streamAll(tenantId).first()
        val features = featureBuilder.build(events)

        val result = mutableMapOf<Int, Double>()

        features.forEach { f ->

            val stock = stockEngine.getStock(
                productId = f.productId,
                locationId = f.locationId
            )

            val input = ModelInput(
                avgDemand7d = f.avgDemand7d.toFloat(),
                avgDemand30d = f.avgDemand30d.toFloat(),
                stockLevel = stock.toFloat(),
                volatility = f.stockVolatility.toFloat()
            )

            val forecast = tfliteEngine
                .predict(input)
                .predictedDemand
                .toDouble()

            result[f.productId] = forecast

            bus.publish(
                WarehouseEvent.ForecastGenerated(
                    productId = f.productId,
                    forecast30Days = forecast
                )
            )
        }

        return result
    }
}