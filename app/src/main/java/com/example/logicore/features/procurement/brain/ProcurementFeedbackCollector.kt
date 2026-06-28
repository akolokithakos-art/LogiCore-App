package com.example.logicore.features.procurement.brain

class ProcurementFeedbackCollector {

    fun record(
        state: ProcurementState,
        action: ProcurementAction,
        result: ActionResult
    ) {

        val reward = calculateReward(state, action, result)

        // later → feed into RL model
    }

    private fun calculateReward(
        state: ProcurementState,
        action: ProcurementAction,
        result: ActionResult
    ): Double {

        return when (result) {

            ActionResult.EXECUTED -> 1.0

            ActionResult.SKIPPED -> -0.2

            ActionResult.IDLE -> 0.0
        }
    }

}
