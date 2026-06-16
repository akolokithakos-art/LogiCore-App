package com.example.logicore.features.warehouse.domain.model

data class ReorderSuggestion(

    val productId: Int,

    val currentStock: Double,

    val forecast30Days: Double,

    val suggestedOrderQty: Double
)