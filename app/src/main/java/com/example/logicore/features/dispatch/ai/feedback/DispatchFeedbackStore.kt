package com.example.logicore.features.dispatch.ai.feedback

import com.example.logicore.features.dispatch.ai.feedback.model.DispatchFeedbackStats

class DispatchFeedbackStore {

    private val stats = mutableMapOf<Int, DispatchFeedbackStats>()

    fun get(vehicleId: Int): DispatchFeedbackStats? {
        return stats[vehicleId]
    }

    fun update(stat: DispatchFeedbackStats) {
        stats[stat.vehicleId] = stat
    }
}