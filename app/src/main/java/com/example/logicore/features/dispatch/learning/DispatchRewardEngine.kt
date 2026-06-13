package com.example.logicore.features.dispatch.learning

import com.example.logicore.features.dispatch.learning.model.DispatchFeedbackEvent

class DispatchRewardEngine {

    fun calculateSuccessRate(events: List<DispatchFeedbackEvent>): Double {

        if (events.isEmpty()) return 0.5

        val success = events.count { it.delivered }
        return success.toDouble() / events.size
    }

    fun calculateTimeScore(events: List<DispatchFeedbackEvent>): Double {

        if (events.isEmpty()) return 0.5

        val avgRatio = events.map {
            it.expectedTimeMinutes / (it.deliveryTimeMinutes + 0.1)
        }.average()

        return avgRatio.coerceIn(0.0, 1.0)
    }

    fun calculatePenalty(events: List<DispatchFeedbackEvent>): Double {

        val lateCount = events.count {
            it.deliveryTimeMinutes > it.expectedTimeMinutes
        }

        return lateCount.toDouble() / (events.size + 1)
    }
}