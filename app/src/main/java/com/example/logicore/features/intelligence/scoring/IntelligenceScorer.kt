package com.example.logicore.features.intelligence.scoring

import com.example.logicore.features.intelligence.core.IntelligenceReport
import com.example.logicore.features.intelligence.core.VehicleIntelSnapshot

class IntelligenceScorer {

    fun score(report: IntelligenceReport): VehicleIntelSnapshot {

        val stockPressure = report.loadPlan.sumOf { it.suggestedQty }
        val demandPressure = report.demand.sumOf { it.recommendedLoad }

        val utilizationScore =
            if (demandPressure + 1 == 0.0) 0.0
            else stockPressure / (demandPressure + 1)

        val riskLevel = when {
            stockPressure > demandPressure * 1.5 -> "OVERLOAD"
            demandPressure > stockPressure -> "UNDERSTOCK"
            else -> "BALANCED"
        }

        return VehicleIntelSnapshot(
            vehicleId = report.vehicleId,
            stockPressure = stockPressure,
            demandPressure = demandPressure,
            utilizationScore = utilizationScore,
            riskLevel = riskLevel
        )
    }
}