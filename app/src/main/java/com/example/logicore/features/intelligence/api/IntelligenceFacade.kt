package com.example.logicore.features.intelligence.api

import com.example.logicore.features.intelligence.core.IntelligenceOrchestrator
import com.example.logicore.features.intelligence.core.VehicleIntelSnapshot
import com.example.logicore.features.intelligence.scoring.IntelligenceScorer

class IntelligenceFacade(
    private val orchestrator: IntelligenceOrchestrator,
    private val scorer: IntelligenceScorer
) {

    suspend fun analyze(vehicleId: Int): VehicleIntelSnapshot {

        val report = orchestrator.evaluateVehicle(vehicleId)

        return scorer.score(report)
    }
}