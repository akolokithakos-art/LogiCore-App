package com.example.logicore.features.warehouse.domain.repository

import com.example.logicore.features.warehouse.domain.model.ProductDemand

interface WarehouseDemandRepository {

    suspend fun getAggregatedDemand():
            List<ProductDemand>
}