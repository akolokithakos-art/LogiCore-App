package com.example.logicore.features.warehouse.domain.model

data class PurchaseOrderDispatchResult(

    val orderId: Int,

    val status: PurchaseOrderDispatchStatus,

    val externalReference: String? = null,

    val message: String
)