package com.example.logicore.dispatch.brain

import com.example.logicore.dispatch.engine.DispatchEngine
import com.example.logicore.dispatch.engine.DriverStateTracker
import com.example.logicore.core.model.Driver
import com.example.logicore.core.model.Order
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class DispatchBrain(
    private val dispatchEngine: DispatchEngine,
    private val driverStateTracker: DriverStateTracker
) {

    private val _assignments = MutableStateFlow(emptyList<Any>())
    val assignments = _assignments.asStateFlow()

    suspend fun runCycle(
        orders: List<Order>,
        drivers: List<Driver>
    ) {
        val result = dispatchEngine.assign(orders, drivers)

        // update driver load state
        result.forEach {
            driverStateTracker.increaseLoad(it.driverId)
        }

        _assignments.value = result
    }
}