package com.example.logicore.features.procurement.rl

data class ProcurementRLState(


    val stockLevel: Double,

    val demandForecast: Double,

    val supplierReliability: Double,

    val priceIndex: Double,

    val riskScore: Double


)
