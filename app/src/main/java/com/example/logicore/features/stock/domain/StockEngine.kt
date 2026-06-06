package com.example.logicore.features.stock.domain

import com.example.logicore.features.stock.data.StockRepository

class StockEngine(
    private val repo: StockRepository
) {

    suspend fun canSell(
        productId: Int,
        vehicleId: Int,
        qty: Double
    ): Boolean {
        val available = repo.getStock(productId, vehicleId)
        return available >= qty
    }

    suspend fun registerSale(
        productId: Int,
        vehicleId: Int,
        qty: Double
    ): Boolean {

        val allowed = canSell(productId, vehicleId, qty)
        if (!allowed) return false

        repo.moveStock(
            productId = productId,
            from = vehicleId,
            to = null,
            qty = qty,
            type = "SALE"
        )

        return true
    }

    suspend fun loadVehicle(
        productId: Int,
        warehouseId: Int,
        vehicleId: Int,
        qty: Double
    ) {
        repo.moveStock(
            productId = productId,
            from = warehouseId,
            to = vehicleId,
            qty = qty,
            type = "LOAD"
        )
    }

    suspend fun returnStock(
        productId: Int,
        vehicleId: Int,
        warehouseId: Int,
        qty: Double
    ) {
        repo.moveStock(
            productId = productId,
            from = vehicleId,
            to = warehouseId,
            qty = qty,
            type = "RETURN"
        )
    }
}