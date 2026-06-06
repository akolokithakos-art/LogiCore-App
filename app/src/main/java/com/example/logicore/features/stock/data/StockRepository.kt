package com.example.logicore.features.stock.data

import com.example.logicore.features.stock.data.local.StockDao
import com.example.logicore.features.stock.data.local.StockMovementEntity

class StockRepository(
    private val dao: StockDao
) {

    suspend fun moveStock(
        productId: Int,
        from: Int?,
        to: Int?,
        qty: Double,
        type: String
    ) {
        dao.insertMovement(
            StockMovementEntity(
                productId = productId,
                fromLocationId = from,
                toLocationId = to,
                quantity = qty,
                type = type
            )
        )
    }

    suspend fun getSalesMovementsByVehicle(vehicleId: Int): List<StockMovementEntity> {
        return dao.getMovementsList()
            .filter { it.fromLocationId == vehicleId && it.type == "SALE" }
    }

    suspend fun getAllMovements(): List<StockMovementEntity> {
        return dao.getMovementsList()
    }

    suspend fun getStock(
        productId: Int,
        locationId: Int
    ): Double {
        return dao.getStockBalance(productId, locationId)
    }
}