package com.example.logicore.features.stock.domain

import com.example.logicore.features.stock.data.StockRepository
import com.example.logicore.features.tenant.core.TenantContext

class StockIngestionService(
    private val repo: StockRepository,
    private val tenantContext: TenantContext
) {

    private fun tenant(): String =
        tenantContext.getTenant()
            ?: throw IllegalStateException("No tenant")

    suspend fun receiveStock(
        productId: Int,
        warehouseId: Int,
        qty: Double
    ) {
        repo.moveStock(
            tenantId = tenant(),
            productId = productId,
            from = null,
            to = warehouseId,
            qty = qty,
            type = "INBOUND_RECEIPT"
        )
    }
}