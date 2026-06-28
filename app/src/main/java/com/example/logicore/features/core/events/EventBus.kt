package com.example.logicore.features.core.events

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class EventBus {

    private val _events = MutableSharedFlow<Any>(
        replay = 0,
        extraBufferCapacity = 1000
    )

    val events = _events.asSharedFlow()

    fun publish(event: Any) {
        val emitted = _events.tryEmit(event)

        if (!emitted) {
            // fallback logging or persistence hook
            println("EVENT DROPPED: $event")
        }
    }
}