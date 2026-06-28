package com.example.logicore.features.procurement.brain

import com.example.logicore.features.procurement.service.ProcurementService

class ProcurementActionEngine(
    private val procurementService: ProcurementService
) {

    suspend fun execute(action: ProcurementAction): ActionResult {

        return when (action) {

            ProcurementAction.BUY_AGGRESSIVE -> {
                procurementService.createBulkOrders()
                ActionResult.EXECUTED
            }

            ProcurementAction.BUY_NORMAL -> {
                procurementService.triggerProcurement()
                ActionResult.EXECUTED
            }

            ProcurementAction.MONITOR -> {
                ActionResult.SKIPPED
            }

            ProcurementAction.DO_NOTHING -> {
                ActionResult.IDLE
            }
        }
    }

}

enum class ActionResult {
    EXECUTED,
    SKIPPED,
    IDLE
}
