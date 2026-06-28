package com.example.logicore.features.events.resilience.retry

data class RetryState(
    val eventId: String,
    val projection: String,
    var attempt: Int = 0,
    var lastError: String? = null
)