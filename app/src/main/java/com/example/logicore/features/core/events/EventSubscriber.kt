package com.example.logicore.features.core.events

interface EventSubscriber<T> {
    suspend fun handle(event: T)
}