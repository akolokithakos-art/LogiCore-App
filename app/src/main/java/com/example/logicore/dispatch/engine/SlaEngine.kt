package com.logicore.dispatch.engine

import com.logicore.core.model.Order

class SlaEngine {

    fun evaluate(
        etaMinutes: Double,
        order: Order
    ): SlaResult {

        val isAtRisk = etaMinutes > order.slaMinutes

        val delayMinutes =
            (etaMinutes - order.slaMinutes)
                .coerceAtLeast(0.0)

        return SlaResult(
            isAtRisk = isAtRisk,
            predictedEta = etaMinutes,
            slaMinutes = order.slaMinutes,
            delayRiskMinutes = delayMinutes
        )
    }
}