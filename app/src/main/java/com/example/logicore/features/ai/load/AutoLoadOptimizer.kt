package com.example.logicore.features.ai.load

import com.example.logicore.features.ai.demand.DemandSignal

class AutoLoadOptimizer(
    private val scoringEngine: LoadScoringEngine
) {

    fun generateLoadPlan(
        signals: List<DemandSignal>,
        maxCapacity: Double
    ): List<DemandSignal> {

        val sorted = signals
            .sortedByDescending { scoringEngine.score(it) }

        val plan = mutableListOf<DemandSignal>()
        var usedCapacity = 0.0

        for (signal in sorted) {

            if (usedCapacity + signal.predictedQty <= maxCapacity) {

                plan.add(signal)
                usedCapacity += signal.predictedQty
            }
        }

        return plan
    }
}