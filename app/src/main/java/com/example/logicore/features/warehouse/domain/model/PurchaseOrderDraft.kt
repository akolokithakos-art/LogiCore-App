package com.example.logicore.features.warehouse.domain.model

data class PurchaseOrderDraft(

    val supplierId: Int,

    val lines: List<PurchaseOrderDraftLine>
)