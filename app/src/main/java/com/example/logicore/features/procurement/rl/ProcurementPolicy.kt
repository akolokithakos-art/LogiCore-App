package com.example.logicore.features.procurement.rl

class ProcurementPolicy(
    private val qNetwork: QNetwork
) {


    fun selectAction(state: ProcurementRLState): ProcurementRLAction {

        val qValues = qNetwork.predict(state)

        return qValues.maxByOrNull { it.value }!!.key
    }


}
