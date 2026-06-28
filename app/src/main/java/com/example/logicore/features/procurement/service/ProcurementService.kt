package com.example.logicore.features.procurement.service

import com.example.logicore.features.procurement.data.local.SupplierProductDao
import com.example.logicore.features.procurement.data.local.PurchaseOrderDao
import com.example.logicore.features.procurement.data.local.PurchaseOrderEntity
import com.example.logicore.features.procurement.data.local.PurchaseOrderLineEntity
import com.example.logicore.features.stock.projection.StockProjectionEntity
import java.util.UUID

class ProcurementService(
    private val supplierProductDao: SupplierProductDao,
    private val orderDao: PurchaseOrderDao
) {


    suspend fun triggerProcurement(projection: StockProjectionEntity) {

        val supplier = supplierProductDao.getPreferredSupplier(
            projection.tenantId,
            projection.productId.toString()
        ) ?: return

        val orderId = UUID.randomUUID().toString()

        val order = PurchaseOrderEntity(
            id = orderId,
            tenantId = projection.tenantId,
            supplierId = supplier.supplierId,
            status = "DRAFT",
            totalItems = 1
        )

        orderDao.insertOrder(order)

        val line = PurchaseOrderLineEntity(
            id = UUID.randomUUID().toString(),
            tenantId = projection.tenantId,
            purchaseOrderId = orderId,
            productId = projection.productId.toString(),
            quantity = calculateReorderQty(projection)
        )

        orderDao.insertLine(line)
    }

    suspend fun triggerProcurement() {
        // trigger general procurement process
    }

    suspend fun createBulkOrders() {
        // create bulk orders for high risk items
    }

    private fun calculateReorderQty(projection: StockProjectionEntity): Double {
        return 10.0 // simple baseline (later AI-driven)
    }


}
