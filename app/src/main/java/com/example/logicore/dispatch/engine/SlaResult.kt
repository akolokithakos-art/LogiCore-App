package com.logicore.dispatch.engine

data class SlaResult(
    val isAtRisk: Boolean,
    val predictedEta: Double,
    val slaMinutes: Double,
    val delayRiskMinutes: Double
)