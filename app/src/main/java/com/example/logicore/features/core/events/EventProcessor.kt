package com.example.logicore.features.core.events

import com.example.logicore.features.core.runtime.AppScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect

class EventProcessor(
    private val bus: EventBus,
    private val dispatcher: EventDispatcher
) {

    fun start() {

        AppScope.scope.launch {

            bus.events.collect { event ->

                try {
                    dispatcher.dispatch(event)
                } catch (e: Exception) {
                    println("EVENT FAILED: $event")
                }
            }
        }
    }
}