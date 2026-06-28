package com.example.logicore.features.procurement.brain

import com.example.logicore.features.stock.projection.StockProjectionEntity

class AutonomousProcurementBrain(
    private val stateBuilder: ProcurementStateBuilder,
    private val policy: ProcurementPolicyBrain,
    private val actionEngine: ProcurementActionEngine,
    private val feedback: ProcurementFeedbackCollector
) {

    suspend fun onTick(projection: StockProjectionEntity) {
        // 1. BUILD STATE (world snapshot)
        val state = stateBuilder.build(projection)

        // 2. DECIDE ACTION (AI policy)
        val action = policy.decide(state)

        // 3. EXECUTE ACTION
        val result = actionEngine.execute(action)

        // 4. COLLECT FEEDBACK
        feedback.record(state, action, result)
    }

}
