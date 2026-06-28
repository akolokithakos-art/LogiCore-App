package com.example.logicore.features.ml.learning

class PredictionEvaluator(
    private val rewardCalculator: RewardCalculator
) {

    fun evaluate(records: List<PredictionRecord>): Double {

        val valid = records.filter { it.actual != null }

        if (valid.isEmpty()) return 0.0

        val rewards = valid.map {
            rewardCalculator.calculate(it.predicted, it.actual!!)
        }

        return rewards.average()
    }
}