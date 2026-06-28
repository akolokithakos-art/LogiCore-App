package com.example.logicore.features.procurement.brain

class ProcurementPolicyBrain {

    fun decide(state: ProcurementState): ProcurementAction {

        val riskScore =
            (state.dailyConsumption * state.trend) -
                    state.stockLevel +
                    (1 - state.supplierReliability) * 10

        return when {

            riskScore > 15 -> ProcurementAction.BUY_AGGRESSIVE

            riskScore > 8 -> ProcurementAction.BUY_NORMAL

            riskScore > 3 -> ProcurementAction.MONITOR

            else -> ProcurementAction.DO_NOTHING
        }
    }

}

enum class ProcurementAction {
    BUY_AGGRESSIVE,
    BUY_NORMAL,
    MONITOR,
    DO_NOTHING
}
