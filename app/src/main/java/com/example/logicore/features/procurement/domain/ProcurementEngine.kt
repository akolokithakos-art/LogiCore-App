package com.example.logicore.features.procurement.domain

import com.example.logicore.features.procurement.data.local.PurchaseOrderEntity
import com.example.logicore.features.procurement.data.local.PurchaseOrderLineEntity
import com.example.logicore.features.stock.domain.StockEngine
import com.example.logicore.features.ml.core.MlEngine
import com.example.logicore.features.tenant.core.TenantContext
import java.util.UUID
import kotlin.math.max

class ProcurementEngine(
    private val stockEngine: StockEngine,
    private val mlEngine: MlEngine,
    private val service: ProcurementService,
    private val tenantContext: TenantContext
) {

    suspend fun createOrder(productId: Int, qty: Double) {

        val tenantId = tenantContext.getTenant() ?: "system"
        val orderId = UUID.randomUUID().toString()

        val order = PurchaseOrderEntity(
            id = orderId,
            tenantId = tenantId,
            supplierId = "0", // TODO: look up supplier for product
            status = "DRAFT",
            totalItems = 1
        )

        val line = PurchaseOrderLineEntity(
            id = UUID.randomUUID().toString(),
            tenantId = tenantId,
            purchaseOrderId = orderId,
            productId = productId.toString(),
            quantity = qty
        )

        service.createOrder(order, listOf(line))
    }

    suspend fun generatePurchaseOrders(
        tenantId: String,
        supplierId: Int,
        warehouseId: Int
    ): Pair<PurchaseOrderEntity, List<PurchaseOrderLineEntity>> {

        val forecast = mlEngine.runForecast(tenantId)
        val orderId = UUID.randomUUID().toString()

        val lines = mutableListOf<PurchaseOrderLineEntity>()

        var totalItems = 0

        forecast.forEach { (productId, predictedDemand) ->

            val currentStock =
                stockEngine.getStock(productId, warehouseId)

            val reorderQty =
                max(predictedDemand - currentStock, 0.0)

            if (reorderQty > 0) {

                lines.add(
                    PurchaseOrderLineEntity(
                        id = UUID.randomUUID().toString(),
                        tenantId = tenantId,
                        purchaseOrderId = orderId,
                        productId = productId.toString(),
                        quantity = reorderQty
                    )
                )

                totalItems++
            }
        }

        val order = PurchaseOrderEntity(
            id = orderId,
            tenantId = tenantId,
            supplierId = supplierId.toString(),
            status = "DRAFT",
            totalItems = totalItems
        )

        return order to lines
    }
}
