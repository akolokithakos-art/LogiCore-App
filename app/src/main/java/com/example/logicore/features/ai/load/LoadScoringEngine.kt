package com.example.logicore.features.ai.load

import com.example.logicore.features.ai.demand.DemandSignal

class LoadScoringEngine {

    fun score(signal: DemandSignal): Double {

        val demandWeight = signal.predictedQty * 0.5

        val historyWeight = signal.historicalAvg * 0.3

        val urgencyWeight = signal.urgencyScore * 0.2

        return demandWeight + historyWeight + urgencyWeight
    }
}