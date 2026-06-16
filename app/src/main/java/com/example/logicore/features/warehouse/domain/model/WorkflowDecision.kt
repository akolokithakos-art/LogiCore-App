package com.example.logicore.features.warehouse.domain.model

data class WorkflowDecision(

    val orderId: Int,

    val status: PurchaseOrderStatus,

    val reason: String
)