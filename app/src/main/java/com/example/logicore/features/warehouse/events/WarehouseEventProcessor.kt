package com.example.logicore.features.warehouse.events

import com.example.logicore.features.warehouse.domain.WorkflowService
import com.example.logicore.features.warehouse.domain.PurchaseOrderDispatcher
import com.example.logicore.features.warehouse.events.model.WarehouseEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class WarehouseEventProcessor(
    private val scope: CoroutineScope,
    private val dispatcher: PurchaseOrderDispatcher,
    private val workflowService: WorkflowService
) {

    fun handle(event: WarehouseEvent) {

        when (event) {

            is WarehouseEvent.PurchaseOrderCreated -> {
                scope.launch {
                    workflowService.process(
                        orderId = event.orderId,
                        totalValue = 0.0, // placeholder hook
                        supplierPriority = 5
                    )
                }
            }

            is WarehouseEvent.PurchaseOrderDispatched -> {
                // audit / sync hooks later
            }

            is WarehouseEvent.ReorderTriggered -> {
                // hook to procurement auto-run
            }

            else -> Unit
        }
    }
}