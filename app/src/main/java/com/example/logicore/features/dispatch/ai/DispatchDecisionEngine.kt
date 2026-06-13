package com.example.logicore.features.dispatch.ai

import com.example.logicore.features.dispatch.ai.model.*
import com.example.logicore.features.dispatch.ai.feedback.DispatchFeedbackStore
import kotlin.math.sqrt

class DispatchDecisionEngine(
    private val feedbackStore: DispatchFeedbackStore
) {

    fun decide(
        request: DispatchRequest,
        vehicles: List<VehicleAIState>
    ): DispatchDecision? {

        var best: DispatchDecision? = null

        for (v in vehicles) {

            val state = v.state

            if (state.activeOrderId != null) continue

            val stats = feedbackStore.get(state.vehicleId)

            val distanceScore = scoreDistance(
                state.lat,
                state.lng,
                request.orderLat,
                request.orderLng
            )

            val capacityScore = scoreCapacity(
                v.capacityUsed,
                request.requiredCapacity
            )

            val historyScore = stats?.successRate ?: 0.5

            val finalScore =
                (distanceScore * 0.4) +
                        (capacityScore * 0.3) +
                        (historyScore * 0.3)

            if (best == null || finalScore > best.score) {

                best = DispatchDecision(
                    vehicleId = state.vehicleId,
                    score = finalScore,
                    reason = "distance + capacity + history"
                )
            }
        }

        return best
    }

    private fun scoreDistance(
        vLat: Double,
        vLng: Double,
        oLat: Double,
        oLng: Double
    ): Double {
        val dx = vLat - oLat
        val dy = vLng - oLng
        val distance = sqrt(dx * dx + dy * dy)
        return 1.0 / (1.0 + distance)
    }

    private fun scoreCapacity(
        used: Double,
        required: Double
    ): Double {
        val remaining = (1.0 - used).coerceAtLeast(0.0)
        return (remaining / (required + 0.1)).coerceIn(0.0, 1.0)
    }
}