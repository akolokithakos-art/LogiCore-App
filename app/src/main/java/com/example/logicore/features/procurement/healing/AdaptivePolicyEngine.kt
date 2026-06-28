package com.example.logicore.features.procurement.healing

import com.example.logicore.features.events.resilience.retry.RetryState
import com.example.logicore.features.events.resilience.retry.RetryPolicy

class AdaptivePolicyEngine {

    fun analyze(
        state: RetryState,
        error: Throwable
    ): FailureDiagnosis {

        val message = error.message ?: ""

        return when {

            message.contains("timeout", ignoreCase = true) ->
                FailureDiagnosis(
                    type = FailureType.TRANSIENT,
                    confidence = 0.8,
                    reason = "Network timeout"
                )

            message.contains("404") ->
                FailureDiagnosis(
                    type = FailureType.SUPPLIER_UNAVAILABLE,
                    confidence = 0.9,
                    reason = "Supplier missing"
                )

            message.contains("constraint", ignoreCase = true) ->
                FailureDiagnosis(
                    type = FailureType.DATA_CONFLICT,
                    confidence = 0.7,
                    reason = "DB constraint violation"
                )

            else ->
                FailureDiagnosis(
                    type = FailureType.UNKNOWN,
                    confidence = 0.3,
                    reason = "Unclassified error"
                )
        }
    }

    fun adapt(diagnosis: FailureDiagnosis): RetryPolicy {

        return when (diagnosis.type) {

            FailureType.TRANSIENT ->
                RetryPolicy(
                    maxRetries = 5,
                    baseDelayMs = 1000
                )

            FailureType.SUPPLIER_UNAVAILABLE ->
                RetryPolicy(
                    maxRetries = 1,
                    baseDelayMs = 0
                )

            FailureType.DATA_CONFLICT ->
                RetryPolicy(
                    maxRetries = 2,
                    baseDelayMs = 3000
                )

            FailureType.UNKNOWN ->
                RetryPolicy(
                    maxRetries = 0,
                    baseDelayMs = 0
                )
        }
    }

    fun scheduleRetry(
        state: RetryState,
        policy: RetryPolicy
    ) {
        // TODO: enqueue into RetryQueueDao with updated policy
    }

    fun switchSupplier(state: RetryState) {
        // TODO: integrate with SupplierProductDao + procurement engine
    }

    fun repairData(state: RetryState) {
        // TODO: event sourcing repair (projection rebuild / correction event)
    }
}