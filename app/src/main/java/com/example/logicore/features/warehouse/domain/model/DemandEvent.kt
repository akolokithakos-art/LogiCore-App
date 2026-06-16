package com.example.logicore.features.warehouse.domain.model

data class DemandEvent(

    val productId: Int,

    val quantity: Double,

    val source: DemandSource,

    val timestamp: Long
)