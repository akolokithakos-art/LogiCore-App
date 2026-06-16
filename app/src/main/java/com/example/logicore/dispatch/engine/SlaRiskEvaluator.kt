package com.example.logicore.dispatch.engine

import com.example.logicore.core.model.Order

class SlaRiskEvaluator {

    fun isRisky(
        etaMinutes: Double,
        order: Order
    ): Boolean {

        return etaMinutes > order.slaMinutes
    }

    fun riskRatio(
        etaMinutes: Double,
        order: Order
    ): Double {

        if (order.slaMinutes <= 0.0) {
            return 1.0
        }

        return etaMinutes / order.slaMinutes
    }
}