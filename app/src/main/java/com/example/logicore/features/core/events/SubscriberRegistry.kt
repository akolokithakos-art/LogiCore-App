package com.example.logicore.features.core.events

class SubscriberRegistry {

    private val subscribers = mutableListOf<EventSubscriber<Any>>()

    fun register(subscriber: EventSubscriber<Any>) {
        subscribers.add(subscriber)
    }

    suspend fun dispatch(event: Any) {
        subscribers.forEach { sub ->
            sub.handle(event)
        }
    }
}