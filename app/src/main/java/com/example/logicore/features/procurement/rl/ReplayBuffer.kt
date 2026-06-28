package com.example.logicore.features.procurement.rl

class ReplayBuffer {


    private val buffer = mutableListOf<Experience>()

    fun add(exp: Experience) {
        buffer.add(exp)
    }

    fun sample(): List<Experience> {
        return buffer.shuffled().take(64)
    }


}

data class Experience(
    val state: ProcurementRLState,
    val action: ProcurementRLAction,
    val reward: Double,
    val nextState: ProcurementRLState
)
