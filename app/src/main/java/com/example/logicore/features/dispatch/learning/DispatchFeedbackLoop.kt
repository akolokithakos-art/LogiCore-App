package com.example.logicore.features.dispatch.learning

import com.example.logicore.features.dispatch.ai.feedback.DispatchFeedbackStore
import com.example.logicore.features.dispatch.learning.model.DispatchFeedbackEvent
import com.example.logicore.features.dispatch.ai.feedback.model.DispatchFeedbackStats

class DispatchFeedbackLoop(
    private val learningStore: DispatchLearningStore,
    private val feedbackStore: DispatchFeedbackStore,
    private val rewardEngine: DispatchRewardEngine
) {

    fun ingest(event: DispatchFeedbackEvent) {

        // 1. store raw event
        learningStore.record(event)

        val vehicleEvents =
            learningStore.getVehicleEvents(event.vehicleId)

        // 2. compute learning metrics
        val successRate =
            rewardEngine.calculateSuccessRate(vehicleEvents)

        val timeScore =
            rewardEngine.calculateTimeScore(vehicleEvents)

        val rejectionRate =
            rewardEngine.calculatePenalty(vehicleEvents)

        // 3. update AI memory (NEW CONTRACT)
        feedbackStore.update(
            DispatchFeedbackStats(
                vehicleId = event.vehicleId,
                successRate = successRate,
                avgDeliveryTimeScore = timeScore,
                rejectionRate = rejectionRate
            )
        )
    }
}