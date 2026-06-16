package com.example.logicore.features.stock.domain

import com.example.logicore.features.stock.domain.model.LoadSuggestion
import com.example.logicore.features.stock.domain.model.StockAlert

class StockIntelligenceEngine(
    private val forecastEngine: StockForecastEngine,
    private val optimizer: AutoLoadOptimizer,
    private val alertEngine: StockAlertEngine
) {

    suspend fun buildVehicleIntelligence(vehicleId: Int): StockIntelligenceReport {

        val forecast = forecastEngine.forecastDailyDemand(vehicleId)
        val loadPlan = optimizer.generatePlan(vehicleId)
        val alerts = alertEngine.generateAlerts(vehicleId)

        return StockIntelligenceReport(
            vehicleId = vehicleId,
            forecast = forecast,
            loadSuggestions = loadPlan,
            alerts = alerts
        )
    }
}