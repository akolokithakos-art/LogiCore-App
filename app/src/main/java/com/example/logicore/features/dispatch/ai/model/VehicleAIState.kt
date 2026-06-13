package com.example.logicore.features.dispatch.ai.model

import com.example.logicore.features.tracking.domain.model.VehicleState

data class VehicleAIState(
    val state: VehicleState,
    val capacityUsed: Double,
    val loadScore: Double = 1.0
)