package com.example.logicore.features.stock.domain.model

data class StockAlert(
    val productId: Int,
    val productName: String,
    val currentStock: Double,
    val recommendedQty: Double,
    val severity: String // LOW / CRITICAL
)