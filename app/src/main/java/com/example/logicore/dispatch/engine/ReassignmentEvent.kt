package com.example.logicore.dispatch.engine

data class ReassignmentEvent(
    val orderId: String,
    val oldDriverId: String,
    val newDriverId: String,
    val reason: String
)