package com.example.logicore.features.warehouse.domain

import com.example.logicore.features.stock.domain.StockEngine
import com.example.logicore.features.warehouse.data.local.SupplierProductEntity
import com.example.logicore.features.warehouse.domain.model.ProcurementSuggestion
import kotlin.math.max

class ProcurementEngine(
    private val stockEngine: StockEngine
) {

    suspend fun generateSuggestions(
        warehouseLocationId: Int,
        supplierProducts: List<SupplierProductEntity>
    ): List<ProcurementSuggestion> {

        return supplierProducts.mapNotNull { rule ->

            val stock =
                stockEngine.getStock(
                    productId = rule.productId,
                    locationId = warehouseLocationId
                )

            if (stock >= rule.reorderPoint)
                return@mapNotNull null

            val suggested =
                max(
                    rule.safetyStock - stock,
                    rule.minOrderQty
                )

            ProcurementSuggestion(
                supplierId = rule.supplierId,
                productId = rule.productId,
                currentStock = stock,
                reorderPoint = rule.reorderPoint,
                suggestedQty = suggested
            )
        }
    }
}