package com.example.logicore.features.procurement.healing

import com.example.logicore.features.events.resilience.retry.RetryState
import com.example.logicore.features.events.resilience.DeadLetterQueue
import com.example.logicore.features.events.data.local.EventStoreEntity

class SelfHealingProcurementEngine(
    private val policyEngine: AdaptivePolicyEngine,
    private val dlq: DeadLetterQueue
) {


    suspend fun handleFailure(
        event: EventStoreEntity,
        state: RetryState,
        error: Throwable
    ) {

        // 1. ANALYZE FAILURE
        val diagnosis = policyEngine.analyze(state, error)

        // 2. ADAPT STRATEGY
        val newPolicy = policyEngine.adapt(diagnosis)

        // 3. DECIDE ACTION
        when (diagnosis.type) {

            FailureType.TRANSIENT -> {
                policyEngine.scheduleRetry(state, newPolicy)
            }

            FailureType.SUPPLIER_UNAVAILABLE -> {
                policyEngine.switchSupplier(state)
            }

            FailureType.DATA_CONFLICT -> {
                policyEngine.repairData(state)
            }

            FailureType.UNKNOWN -> {
                dlq.push(
                    event = event,
                    error = error.message ?: "unknown",
                    projection = state.projection
                )
            }
        }
    }

}
