package com.example.logicore.features.warehouse.data

import com.example.logicore.features.warehouse.domain.engine.DemandAggregationEngine
import com.example.logicore.features.warehouse.domain.model.DemandEvent
import com.example.logicore.features.warehouse.domain.model.ProductDemand
import com.example.logicore.features.warehouse.domain.repository.WarehouseDemandRepository

class WarehouseDemandRepositoryImpl(
    private val aggregationEngine: DemandAggregationEngine
) : WarehouseDemandRepository {

    override suspend fun getAggregatedDemand():
            List<ProductDemand> {

        val events =
            loadDemandEvents()

        return aggregationEngine.aggregate(events)
    }

    private suspend fun loadDemandEvents():
            List<DemandEvent> {

        return emptyList()
    }
}