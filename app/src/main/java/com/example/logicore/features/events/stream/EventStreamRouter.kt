package com.example.logicore.features.events.stream

import com.example.logicore.features.core.events.EventBus
import com.example.logicore.features.events.registry.ProjectionRegistry
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class EventStreamRouter(
    private val eventBus: EventBus,
    private val registry: ProjectionRegistry,
    private val scope: CoroutineScope
) {

    fun start() {

        scope.launch {

            eventBus.events.collect { event ->

                if (event !is com.example.logicore.features.events.data.local.EventStoreEntity) {
                    return@collect
                }

                val targets = registry.resolve(event)

                targets.forEach { projection ->

                    launch {

                        try {
                            projection.handle(event)
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                }
            }
        }
    }
}