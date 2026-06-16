package com.example.logicore.features.warehouse.domain

import com.example.logicore.features.warehouse.data.local.SupplierProductEntity
import com.example.logicore.features.warehouse.domain.model.WarehouseProcurementResult

class WarehouseProcurementService(
    private val procurementEngine: ProcurementEngine,
    private val poGenerator: PurchaseOrderGenerator
) {

    suspend fun run(
        warehouseLocationId: Int,
        supplierProducts: List<SupplierProductEntity>
    ): WarehouseProcurementResult {

        val suggestions =
            procurementEngine.generateSuggestions(
                warehouseLocationId,
                supplierProducts
            )

        val purchaseOrders =
            poGenerator.generate(
                suggestions
            )

        return WarehouseProcurementResult(
            suggestions = suggestions,
            purchaseOrders = purchaseOrders
        )
    }
}