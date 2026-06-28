package com.example.logicore.features.procurement.rl

class ProcurementRewardCalculator {


    fun calculate(
        state: ProcurementRLState,
        action: ProcurementRLAction,
        nextState: ProcurementRLState
    ): Double {

        var reward = 0.0

        // stock availability reward
        if (nextState.stockLevel > 0) reward += 1.0

        // penalty for stockout risk
        if (nextState.stockLevel <= 0) reward -= 5.0

        // cost efficiency reward
        reward += (1.0 - nextState.priceIndex)

        // supplier stability reward
        reward += nextState.supplierReliability * 2

        return reward
    }


}
