package com.example.logicore.features.brain.learning

import com.example.logicore.features.dispatch.domain.DispatchDecision
import com.example.logicore.features.rl.state.DispatchState

class LearningLayer {

    fun refine(
        decision: DispatchDecision,
        state: DispatchState
    ): DispatchDecision {

        // RL policy adjustment hook

        return decision.copy(
            score = decision.score * 1.05
        )
    }
}