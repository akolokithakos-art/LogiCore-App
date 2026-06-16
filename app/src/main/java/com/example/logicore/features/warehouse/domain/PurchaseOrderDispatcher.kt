package com.example.logicore.features.warehouse.domain

import com.example.logicore.features.warehouse.data.local.PurchaseOrderDao
import com.example.logicore.features.warehouse.integration.SupplierApi
import com.example.logicore.features.warehouse.domain.model.*

class PurchaseOrderDispatcher(
    private val dao: PurchaseOrderDao,
    private val api: SupplierApi
) {

    suspend fun dispatch(
        orderId: Int,
        supplierId: Int,
        payload: String
    ): PurchaseOrderDispatchResult {

        return try {

            val externalRef =
                api.sendPurchaseOrder(
                    supplierId,
                    payload
                )

            dao.updateStatus(
                orderId,
                "SENT"
            )

            PurchaseOrderDispatchResult(
                orderId = orderId,
                status = PurchaseOrderDispatchStatus.SENT,
                externalReference = externalRef,
                message = "Successfully sent to supplier"
            )

        } catch (e: Exception) {

            PurchaseOrderDispatchResult(
                orderId = orderId,
                status = PurchaseOrderDispatchStatus.FAILED,
                message = e.message ?: "Unknown error"
            )
        }
    }
}