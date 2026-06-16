package com.example.logicore.features.intelligence.forecast

import com.example.logicore.features.tracking.domain.model.VehicleState

/**
 * Suggests moving vans BEFORE stockout happens
 */
class RepositioningEngine {

    fun suggestReposition(
        vehicles: List<VehicleState>,
        heatmap: List<ZoneHeat>
    ): List<RepositionPlan> {

        val hottestZone = heatmap.maxByOrNull { it.pressure }
            ?: return emptyList()

        return vehicles.mapNotNull { v ->

            if (v.activeOrderId != null) return@mapNotNull null

            val distancePenalty = kotlin.math.abs(v.lat + v.lng) * 0.001

            val priority = hottestZone.pressure - distancePenalty

            if (priority > 50) {
                RepositionPlan(
                    vehicleId = v.vehicleId,
                    targetZoneId = hottestZone.zoneId,
                    priority = priority
                )
            } else null
        }
    }
}