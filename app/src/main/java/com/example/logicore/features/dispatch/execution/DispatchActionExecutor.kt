package com.example.logicore.features.dispatch.execution

import com.example.logicore.features.dispatch.decision.DispatchAction
import com.example.logicore.features.stock.domain.StockEngine

class DispatchActionExecutor(
    private val stockEngine: StockEngine
) {

    suspend fun execute(action: DispatchAction): ExecutionResult {

        return when (action) {

            is DispatchAction.ContinueRoute -> {

                ExecutionResult.Success(
                    "Route συνεχίζεται για vehicle ${action.vehicleId}"
                )
            }

            is DispatchAction.Reroute -> {

                ExecutionResult.Success(
                    "Reroute ενεργοποιήθηκε για vehicle ${action.vehicleId}"
                )
            }

            is DispatchAction.EmergencyReload -> {

                ExecutionResult.Success(
                    "Emergency reload για vehicle ${action.vehicleId} από warehouse ${action.warehouseId}"
                )
            }

            is DispatchAction.StopAndValidate -> {

                ExecutionResult.Success(
                    "Stop & validate για vehicle ${action.vehicleId}: ${action.reason}"
                )
            }
        }
    }
}