package com.example.logicore.features.rl.reward

object RewardCalculator {

    fun calculate(
        deliveredOnTime: Boolean,
        etaError: Double,
        distanceKm: Double
    ): Double {

        var reward = 0.0

        if (deliveredOnTime) reward += 10.0 else reward -= 10.0

        reward -= etaError * 0.5
        reward -= distanceKm * 0.1

        return reward
    }
}