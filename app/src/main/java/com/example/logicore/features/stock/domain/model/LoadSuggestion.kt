package com.example.logicore.features.stock.domain.model

data class LoadSuggestion(
    val productId: Int,
    val currentStock: Double,
    val avgDailyDemand: Double,
    val suggestedQty: Double
)