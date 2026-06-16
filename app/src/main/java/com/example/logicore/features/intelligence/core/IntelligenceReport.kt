package com.example.logicore.features.intelligence.core

import com.example.logicore.features.ai.domain.model.ZoneDemand
import com.example.logicore.features.stock.domain.model.LoadSuggestion
import com.example.logicore.features.dispatch.ai.model.DispatchDecision

data class IntelligenceReport(
    val vehicleId: Int,
    val demand: List<ZoneDemand>,
    val loadPlan: List<LoadSuggestion>,
    val dispatchSuggestion: DispatchDecision?
)