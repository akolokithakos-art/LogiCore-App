package com.example.logicore.features.procurement.domain

data class ProductReorderPolicy(

    val tenantId: String,

    val productId: Long,

    val minimumStock: Double,

    val reorderQuantity: Double
)