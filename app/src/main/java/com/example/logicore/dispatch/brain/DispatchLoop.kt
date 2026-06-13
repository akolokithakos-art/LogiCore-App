package com.logicore.dispatch.brain

import com.logicore.core.model.Driver
import com.logicore.core.model.Order
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class DispatchLoop(
    private val brain: DispatchBrain,
    private val intervalMs: Long = 5000L
) {

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    private val _running = MutableStateFlow(false)
    val running = _running.asStateFlow()

    private var job: Job? = null

    fun start(
        ordersProvider: suspend () -> List<Order>,
        driversProvider: suspend () -> List<Driver>
    ) {
        if (job != null) return

        _running.value = true

        job = scope.launch {
            while (isActive) {

                try {
                    val orders = ordersProvider()
                    val drivers = driversProvider()

                    if (orders.isNotEmpty() && drivers.isNotEmpty()) {
                        brain.runCycle(orders, drivers)
                    }

                } catch (e: Exception) {
                    e.printStackTrace()
                }

                delay(intervalMs)
            }
        }
    }

    fun stop() {
        job?.cancel()
        job = null
        _running.value = false
    }
}