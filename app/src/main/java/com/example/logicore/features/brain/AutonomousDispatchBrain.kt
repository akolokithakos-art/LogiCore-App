package com.example.logicore.features.brain

import com.example.logicore.features.brain.perception.PerceptionLayer
import com.example.logicore.features.brain.prediction.PredictionLayer
import com.example.logicore.features.brain.decision.DecisionLayer
import com.example.logicore.features.brain.learning.LearningLayer
import com.example.logicore.features.brain.healing.SelfHealingLayer
import com.example.logicore.features.dispatch.domain.DispatchRequest
import com.example.logicore.features.dispatch.domain.DispatchDecision

class AutonomousDispatchBrain(
    private val perception: PerceptionLayer,
    private val predictor: PredictionLayer,
    private val decision: DecisionLayer,
    private val learner: LearningLayer,
    private val healer: SelfHealingLayer
) {

    suspend fun process(request: DispatchRequest): DispatchDecision {

        val state = perception.buildState(request)

        val prediction = predictor.predict(state)

        val candidate = decision.select(state, prediction)

        val finalDecision = learner.refine(candidate, state)

        healer.observe(finalDecision)

        return finalDecision
    }
}