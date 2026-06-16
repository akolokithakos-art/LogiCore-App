package com.example.logicore.features.warehouse.integration

class SupplierApi {

    fun sendPurchaseOrder(
        supplierId: Int,
        payload: String
    ): String {

        // MOCK external system call (ERP / Email / EDI / REST)
        return "PO-${System.currentTimeMillis()}"
    }
}