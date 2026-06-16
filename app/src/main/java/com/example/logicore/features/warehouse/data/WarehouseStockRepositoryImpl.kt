package com.example.logicore.features.warehouse.data

import com.example.logicore.features.stock.domain.StockEngine
import com.example.logicore.features.warehouse.domain.model.WarehouseStock
import com.example.logicore.features.warehouse.domain.repository.WarehouseStockRepository

class WarehouseStockRepositoryImpl(
    private val stockEngine: StockEngine,
    private val warehouseLocationId: Int
) : WarehouseStockRepository {

    override suspend fun getStock(
        productId: Int
    ): WarehouseStock {

        val available =
            stockEngine.getStock(
                productId = productId,
                locationId = warehouseLocationId
            )

        return WarehouseStock(
            productId = productId,
            available = available,
            reserved = 0.0,
            safetyStock = 10.0
        )
    }
}