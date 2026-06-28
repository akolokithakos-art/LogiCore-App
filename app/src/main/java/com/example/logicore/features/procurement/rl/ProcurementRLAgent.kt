package com.example.logicore.features.procurement.rl

class ProcurementRLAgent(


    private val policy: ProcurementPolicy,
    private val rewardCalculator: ProcurementRewardCalculator,
    private val replayBuffer: ReplayBuffer


) {


    fun act(state: ProcurementRLState): ProcurementRLAction {
        return policy.selectAction(state)
    }

    fun learn(
        state: ProcurementRLState,
        action: ProcurementRLAction,
        nextState: ProcurementRLState
    ) {

        val reward = rewardCalculator.calculate(state, action, nextState)

        replayBuffer.add(
            Experience(state, action, reward, nextState)
        )
    }


}
