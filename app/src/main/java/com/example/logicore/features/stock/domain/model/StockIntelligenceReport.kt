package com.example.logicore.features.stock.domain.model

data class StockIntelligenceReport(
    val vehicleId: Int,
    val forecast: Map<Int, Double>,
    val loadSuggestions: List<LoadSuggestion>,
    val alerts: List<StockAlert>
)