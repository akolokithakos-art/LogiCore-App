package com.example.logicore.features.warehouse.domain.repository

import com.example.logicore.features.warehouse.domain.model.WarehouseStock

interface WarehouseStockRepository {

    suspend fun getStock(
        productId: Int
    ): WarehouseStock
}