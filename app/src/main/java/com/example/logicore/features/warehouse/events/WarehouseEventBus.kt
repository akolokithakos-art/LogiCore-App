package com.example.logicore.features.warehouse.events

import com.example.logicore.features.warehouse.events.model.WarehouseEvent
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class WarehouseEventBus {

    private val _events = MutableSharedFlow<WarehouseEvent>(extraBufferCapacity = 100)
    val events = _events.asSharedFlow()

    fun publish(event: WarehouseEvent) {
        _events.tryEmit(event)
    }
}