package com.example.logicore.brain.dispatch

import com.example.logicore.dispatch.engine.DispatchEngine
import com.example.logicore.core.model.Driver
import com.example.logicore.core.model.Order

class DispatchBrain(
    private val dispatchEngine: DispatchEngine
) {

    suspend fun run(
        orders: List<Order>,
        drivers: List<Driver>
    ) = dispatchEngine.assign(orders, drivers)
}