package com.example.logicore.features.warehouse.data

import com.example.logicore.features.warehouse.data.local.*
import com.example.logicore.features.warehouse.domain.model.PurchaseOrderDraft

class PurchaseOrderRepository(
    private val dao: PurchaseOrderDao
) {

    suspend fun saveDraft(
        tenantId: String,
        draft: PurchaseOrderDraft
    ): Long {

        val orderId = dao.insertOrder(
            PurchaseOrderEntity(
                tenantId = tenantId,
                supplierId = draft.supplierId,
                status = "DRAFT"
            )
        )

        draft.lines.forEach { line ->

            dao.insertLine(
                PurchaseOrderLineEntity(
                    purchaseOrderId = orderId.toInt(),
                    productId = line.productId,
                    quantity = line.quantity
                )
            )
        }

        return orderId
    }
}