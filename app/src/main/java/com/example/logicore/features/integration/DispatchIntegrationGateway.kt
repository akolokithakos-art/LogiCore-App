package com.example.logicore.features.integration

import com.example.logicore.features.dispatch.execution.ExecutionResult
import com.example.logicore.features.integration.local.DispatchQueueDao
import com.example.logicore.features.integration.local.DispatchOperation
import java.util.UUID

class DispatchIntegrationGateway(
    private val dao: DispatchQueueDao
) {

    suspend fun registerOperation(
        vehicleId: Int,
        type: String,
        payload: String
    ): ExecutionResult {

        val op = DispatchOperation(
            operationId = UUID.randomUUID().toString(),
            type = type,
            payload = payload,
            timestamp = System.currentTimeMillis(),
            synced = false
        )

        dao.enqueue(op)

        return ExecutionResult.Success("Operation queued")
    }
}