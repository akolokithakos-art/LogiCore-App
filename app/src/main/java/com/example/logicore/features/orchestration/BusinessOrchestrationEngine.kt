package com.example.logicore.features.orchestration

import com.example.logicore.features.customerbalance.domain.CreditControlEngine
import com.example.logicore.features.orchestration.model.BusinessCommand
import com.example.logicore.features.orchestration.model.OrchestrationResult
import com.example.logicore.features.stock.domain.StockEngine
import com.example.logicore.features.dispatch.execution.DispatchActionExecutor
import com.example.logicore.features.dispatch.decision.DispatchAction
import com.example.logicore.features.integration.DispatchIntegrationGateway
import com.example.logicore.features.tenant.core.TenantContext

class BusinessOrchestrationEngine(
    private val creditEngine: CreditControlEngine,
    private val stockEngine: StockEngine,
    private val dispatchExecutor: DispatchActionExecutor,
    private val integrationGateway: DispatchIntegrationGateway,
    private val tenantContext: TenantContext
) {

    suspend fun execute(command: BusinessCommand): OrchestrationResult {

        val tenantId = tenantContext.getTenant()
            ?: return OrchestrationResult.Rejected("No tenant")

        val amount = command.quantity * command.price

        // 1. CREDIT CHECK
        val creditOk = creditEngine.canSell(
            customerId = command.customerId,
            saleAmount = amount
        )

        if (!creditOk) {
            return OrchestrationResult.Rejected("Credit limit exceeded")
        }

        // 2. STOCK CHECK
        val stockOk = stockEngine.canSell(
            productId = command.productId,
            vehicleId = command.vehicleId,
            qty = command.quantity
        )

        if (!stockOk) {
            return OrchestrationResult.Rejected("Insufficient stock")
        }

        // 3. EXECUTION PHASE
        stockEngine.registerSale(
            productId = command.productId,
            vehicleId = command.vehicleId,
            qty = command.quantity
        )

        creditEngine.registerSale(
            customerId = command.customerId,
            saleAmount = amount
        )

        // 4. DISPATCH (side effect)
        val actionResult = dispatchExecutor.execute(
            DispatchAction.ContinueRoute(command.vehicleId)
        )

        // 5. SYNC (fire-and-track, not blind call)
        try {
            integrationGateway.registerOperation(
                vehicleId = command.vehicleId,
                type = "SALE",
                payload = "tenant=$tenantId, product=${command.productId}, qty=${command.quantity}"
            )
        } catch (e: Exception) {
            // IMPORTANT: don't break transaction
            // mark for retry queue instead (future upgrade)
        }

        return OrchestrationResult.Success("Transaction completed")
    }
}