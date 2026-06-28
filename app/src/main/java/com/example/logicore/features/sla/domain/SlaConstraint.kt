package com.example.logicore.features.sla.domain

data class SlaConstraint(

    val tenantId: String,

    val orderId: String,

    val maxDeliveryMinutes: Int,

    val priority: Int
)