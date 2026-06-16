package com.example.logicore.features.warehouse.domain

import com.example.logicore.features.warehouse.data.local.PurchaseOrderDao
import com.example.logicore.features.warehouse.domain.model.PurchaseOrderStatus
import com.example.logicore.features.warehouse.domain.model.WorkflowDecision

class WorkflowService(
    private val dao: PurchaseOrderDao,
    private val engine: WorkflowEngine
) {

    suspend fun process(
        orderId: Int,
        totalValue: Double,
        supplierPriority: Int
    ): WorkflowDecision {

        val decision =
            engine.evaluate(
                orderId,
                totalValue,
                supplierPriority
            )

        dao.updateStatus(
            orderId,
            decision.status.name
        )

        return decision
    }
}