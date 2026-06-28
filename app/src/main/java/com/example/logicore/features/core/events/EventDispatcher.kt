package com.example.logicore.features.core.events

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EventDispatcher(
    private val bus: EventBus,
    private val registry: SubscriberRegistry
) {

    suspend fun dispatch(event: Any) {
        registry.dispatch(event)
    }

    fun start(scope: CoroutineScope) {

        scope.launch(Dispatchers.IO) {

            bus.events.collect { event ->
                registry.dispatch(event)
            }
        }
    }
}