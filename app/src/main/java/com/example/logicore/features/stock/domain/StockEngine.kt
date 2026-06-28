package com.example.logicore.features.stock.domain

import com.example.logicore.features.stock.data.StockRepository
import com.example.logicore.features.tenant.core.TenantContext

class StockEngine(
    private val repo: StockRepository,
    private val tenantContext: TenantContext
) {

    private fun tenant(): String =
        tenantContext.getTenant() ?: throw IllegalStateException("No tenant")

    suspend fun getStock(productId: Int, locationId: Int): Double {
        return repo.getStock(
            tenantId = tenant(),
            productId = productId,
            locationId = locationId
        )
    }

    suspend fun canSell(productId: Int, vehicleId: Int, qty: Double): Boolean {
        val available = getStock(productId, vehicleId)
        return available >= qty
    }

    suspend fun registerSale(productId: Int, vehicleId: Int, qty: Double): Boolean {
        if (!canSell(productId, vehicleId, qty)) return false

        repo.moveStock(
            tenantId = tenant(),
            productId = productId,
            from = vehicleId,
            to = null,
            qty = qty,
            type = "SALE"
        )

        return true
    }

    suspend fun addStock(productId: Int, warehouseId: Int, qty: Double) {
        repo.moveStock(
            tenantId = tenant(),
            productId = productId,
            from = null,
            to = warehouseId,
            qty = qty,
            type = "ADJUSTMENT_IN"
        )
    }

    suspend fun removeStock(productId: Int, qty: Double) {
        repo.moveStock(
            tenantId = tenant(),
            productId = productId,
            from = 0, // Default warehouse or location
            to = null,
            qty = qty,
            type = "ADJUSTMENT_OUT"
        )
    }
}
