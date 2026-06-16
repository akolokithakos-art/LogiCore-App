package com.example.logicore.features.warehouse.events

import com.example.logicore.features.warehouse.events.model.WarehouseEvent

class WarehouseEventPublisher(
    private val bus: WarehouseEventBus,
    private val processor: WarehouseEventProcessor
) {

    fun publish(event: WarehouseEvent) {

        bus.publish(event)
        processor.handle(event)
    }
}