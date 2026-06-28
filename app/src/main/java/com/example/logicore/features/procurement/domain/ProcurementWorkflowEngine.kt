package com.example.logicore.features.procurement.domain

import com.example.logicore.features.core.events.EventBus
import com.example.logicore.features.procurement.data.local.PurchaseOrderDao
import com.example.logicore.features.procurement.events.ProcurementEvent

class ProcurementWorkflowEngine(
    private val dao: PurchaseOrderDao,
    private val eventBus: EventBus
) {

    suspend fun markAsSent(orderId: Long, supplierId: Int) {

        val order = dao.getOrders("dummy")
            .first { it.id.toLong() == orderId }

        dao.insertOrder(
            order.copy(status = "SENT")
        )

        eventBus.publish(
            ProcurementEvent.OrderSent(orderId, supplierId)
        )
    }

    suspend fun markAsReceived(orderId: Long) {

        val order = dao.getOrders("dummy")
            .first { it.id.toLong() == orderId }

        dao.insertOrder(
            order.copy(status = "RECEIVED")
        )

        eventBus.publish(
            ProcurementEvent.OrderReceived(orderId)
        )
    }
}