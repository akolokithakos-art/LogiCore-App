package com.example.logicore.features.procurement.domain

import com.example.logicore.features.procurement.data.local.PurchaseOrderDao
import com.example.logicore.features.procurement.data.local.PurchaseOrderEntity
import com.example.logicore.features.procurement.data.local.PurchaseOrderLineEntity

class ProcurementService(
    private val dao: PurchaseOrderDao
) {

    suspend fun createOrder(
        order: PurchaseOrderEntity,
        lines: List<PurchaseOrderLineEntity>
    ) {

        dao.insertOrder(order)

        lines.forEach {
            dao.insertLine(it)
        }
    }
}