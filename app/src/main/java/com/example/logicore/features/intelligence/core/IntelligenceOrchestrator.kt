package com.example.logicore.features.intelligence.core

import com.example.logicore.features.ai.domain.RouteDemandAI
import com.example.logicore.features.stock.domain.AutoLoadOptimizer
import com.example.logicore.features.dispatch.ai.DispatchDecisionEngine
import com.example.logicore.features.dispatch.ai.model.DispatchDecision
import com.example.logicore.features.dispatch.ai.model.DispatchRequest

class IntelligenceOrchestrator(
    private val demandAI: RouteDemandAI,
    private val autoLoadOptimizer: AutoLoadOptimizer,
    private val dispatchAI: DispatchDecisionEngine
) {

    suspend fun evaluateVehicle(vehicleId: Int): IntelligenceReport {

        val demand = demandAI.analyze(vehicleId)
        val loadPlan = autoLoadOptimizer.generatePlan(vehicleId)

        val requiredCapacity = loadPlan.sumOf { it.suggestedQty }

        val dispatch = dispatchAI.decide(
            request = DispatchRequest(
                orderLat = 0.0,
                orderLng = 0.0,
                requiredCapacity = requiredCapacity
            ),
            vehicles = emptyList()
        )

        return IntelligenceReport(
            vehicleId = vehicleId,
            demand = demand,
            loadPlan = loadPlan,
            dispatchSuggestion = dispatch
        )
    }
}