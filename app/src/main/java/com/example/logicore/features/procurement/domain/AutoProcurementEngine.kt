package com.example.logicore.features.procurement.domain

import com.example.logicore.features.procurement.data.local.SupplierProductDao
import com.example.logicore.features.procurement.data.local.PurchaseOrderDao
import com.example.logicore.features.stock.projection.StockProjectionDao

class AutoProcurementEngine(
    private val stockProjectionDao: StockProjectionDao,
    private val supplierProductDao: SupplierProductDao,
    private val purchaseOrderDao: PurchaseOrderDao
) {

    suspend fun evaluate(
        tenantId: String,
        policy: ProductReorderPolicy,
        warehouseLocationId: Int
    ) {

        val stock = stockProjectionDao.find(
            tenantId = tenantId,
            productId = policy.productId.toInt(),
            locationId = warehouseLocationId
        ) ?: return

        if (stock.quantity > policy.minimumStock) {
            return
        }

        val preferredSupplier =
            supplierProductDao.getPreferredSupplier(
                tenantId,
                policy.productId.toString()
            ) ?: return

        createPurchaseOrder(
            tenantId = tenantId,
            supplierId = preferredSupplier.supplierId.toLongOrNull() ?: 0L,
            productId = policy.productId,
            quantity = policy.reorderQuantity
        )
    }

    private suspend fun createPurchaseOrder(
        tenantId: String,
        supplierId: Long,
        productId: Long,
        quantity: Double
    ) {

        // εδώ θα δημιουργούμε PurchaseOrder
    }
}