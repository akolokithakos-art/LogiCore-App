package com.example.logicore.features.sla.engine

import com.example.logicore.features.sla.domain.*
import com.example.logicore.features.routing.domain.RouteScore

class SlaRouteOptimizer {

    fun evaluate(
        driverId: String,
        vehicleId: String,
        route: RouteScore,
        sla: SlaConstraint
    ): OptimizedAssignment {

        val timePenalty =
            if (route.etaMinutes > sla.maxDeliveryMinutes) 2.0 else 0.0

        val risk = route.riskScore + timePenalty

        val score =
            (100.0 - route.distanceKm)
        - (route.etaMinutes * 0.5)
        - risk

        return OptimizedAssignment(
            driverId = driverId,
            vehicleId = vehicleId,
            totalScore = score,
            estimatedEta = route.etaMinutes,
            slaRisk = risk
        )
    }
}