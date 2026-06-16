package com.example.logicore.features.warehouse.domain

import com.example.logicore.features.tenant.core.TenantContext
import com.example.logicore.features.warehouse.data.PurchaseOrderRepository
import com.example.logicore.features.warehouse.data.local.SupplierProductEntity

class PurchaseOrderService(
    private val procurementService: WarehouseProcurementService,
    private val repository: PurchaseOrderRepository,
    private val tenantContext: TenantContext
) {

    private fun tenant(): String =
        tenantContext.getTenant()
            ?: throw IllegalStateException("No tenant selected")

    suspend fun runAutoProcurement(
        warehouseLocationId: Int,
        supplierProducts: List<SupplierProductEntity>
    ): List<Long> {

        val result =
            procurementService.run(
                warehouseLocationId,
                supplierProducts
            )

        return result.purchaseOrders.map { draft ->

            repository.saveDraft(
                tenantId = tenant(),
                draft = draft
            )
        }
    }
}