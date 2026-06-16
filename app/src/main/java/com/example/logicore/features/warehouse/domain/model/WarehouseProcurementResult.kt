package com.example.logicore.features.warehouse.domain.model

data class WarehouseProcurementResult(

    val suggestions: List<ProcurementSuggestion>,

    val purchaseOrders: List<PurchaseOrderDraft>
)