package com.example.logicore.features.warehouse.domain

import com.example.logicore.features.core.events.EventBus
import com.example.logicore.features.procurement.events.ProcurementEvent
import com.example.logicore.features.procurement.data.local.PurchaseOrderDao
import com.example.logicore.features.stock.domain.StockIngestionService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class WarehouseReceivingEngine(
    private val eventBus: EventBus,
    private val purchaseOrderDao: PurchaseOrderDao,
    private val stockIngestionService: StockIngestionService,
    private val scope: CoroutineScope
) {

    fun start(tenantId: String) {

        scope.launch {

            eventBus.events.collect { event ->

                when (event) {

                    is ProcurementEvent.OrderReceived -> {

                        val lines = purchaseOrderDao.getLinesForOrder(
                            tenantId = tenantId,
                            orderId = event.orderId.toString()
                        )

                        lines.forEach { line ->

                            stockIngestionService.receiveStock(
                                productId = line.productId.toInt(),
                                warehouseId = 0, // TODO: warehouse mapping
                                qty = line.quantity
                            )
                        }
                    }

                    else -> {}
                }
            }
        }
    }
}