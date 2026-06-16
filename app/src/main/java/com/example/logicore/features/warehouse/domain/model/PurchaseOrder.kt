package com.example.logicore.features.warehouse.domain.model

data class PurchaseOrder(
    val productId: Int,
    val quantity: Double,
    val priority: String, // LOW / MEDIUM / HIGH / CRITICAL
    val reason: String
)