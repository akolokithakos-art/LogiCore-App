package com.example.logicore.features.warehouse.domain.model

data class ProcurementSuggestion(

    val supplierId: Int,

    val productId: Int,

    val currentStock: Double,

    val reorderPoint: Double,

    val suggestedQty: Double
)