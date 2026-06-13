package com.example.logicore.features.stock.data

import com.example.logicore.features.stock.data.local.StockDao
import com.example.logicore.features.stock.data.local.StockMovementEntity

class StockRepository(
    private val dao: StockDao
) {

    suspend fun moveStock(
        tenantId: String,
        productId: Int,
        from: Int?,
        to: Int?,
        qty: Double,
        type: String
    ) {
        dao.insertMovement(
            StockMovementEntity(
                tenantId = tenantId,
                productId = productId,
                fromLocationId = from,
                toLocationId = to,
                quantity = qty,
                type = type
            )
        )
    }

    suspend fun getAllMovements(tenantId: String): List<StockMovementEntity> {
        return dao.getMovementsList(tenantId)
    }

    suspend fun getStock(
        tenantId: String,
        productId: Int,
        locationId: Int
    ): Double {
        return dao.getStockBalance(
            tenantId = tenantId,
            productId = productId,
            locationId = locationId
        )
    }
}