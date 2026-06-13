package com.example.logicore.features.orchestration.model

data class BusinessCommand(

    val customerId: Int,
    val vehicleId: Int,

    val productId: Int,
    val quantity: Double,
    val price: Double,

    val routeId: Int? = null
)