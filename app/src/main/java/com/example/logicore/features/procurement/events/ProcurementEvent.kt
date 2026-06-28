package com.example.logicore.features.procurement.events

sealed class ProcurementEvent {

    data class OrderCreated(
        val orderId: Long,
        val tenantId: String
    ) : ProcurementEvent()

    data class OrderSent(
        val orderId: Long,
        val supplierId: Int
    ) : ProcurementEvent()

    data class OrderReceived(
        val orderId: Long
    ) : ProcurementEvent()

    data class OrderCancelled(
        val orderId: Long
    ) : ProcurementEvent()
}