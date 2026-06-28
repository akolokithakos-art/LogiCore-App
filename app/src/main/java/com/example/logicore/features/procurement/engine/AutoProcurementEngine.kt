package com.example.logicore.features.procurement.engine

import com.example.logicore.features.stock.projection.StockProjectionEntity
import com.example.logicore.features.procurement.service.ProcurementService

class AutoProcurementEngine(
    private val service: ProcurementService
) {

    suspend fun evaluate(projection: StockProjectionEntity) {

        if (projection.quantity > projection.reorderThreshold) {
            return
        }

        service.triggerProcurement(projection)
    }

}
