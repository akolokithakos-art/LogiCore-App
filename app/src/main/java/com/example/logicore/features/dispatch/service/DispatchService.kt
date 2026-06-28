package com.example.logicore.features.dispatch.service

import com.example.logicore.features.dispatch.engine.DispatchEngine
import com.example.logicore.features.dispatch.domain.DispatchRequest
import com.example.logicore.features.dispatch.domain.DispatchDecision

class DispatchService(
    private val engine: DispatchEngine
) {

    suspend fun assign(request: DispatchRequest): DispatchDecision? {

        return engine.dispatch(request)
    }
}