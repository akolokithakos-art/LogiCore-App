package com.example.logicore.features.intelligence.forecast

import com.example.logicore.features.ai.domain.model.ZoneDemand

/**
 * Produces zone pressure score (for repositioning decisions)
 */
class RouteHeatmapAI {

    fun buildHeatmap(demand: List<ZoneDemand>): List<ZoneHeat> {

        return demand.groupBy { it.zoneId }
            .map { (zoneId, list) ->

                val pressure = list.sumOf { it.recommendedLoad }

                val intensity = when {
                    pressure > 100 -> "RED"
                    pressure > 50 -> "ORANGE"
                    else -> "GREEN"
                }

                ZoneHeat(
                    zoneId = zoneId,
                    pressure = pressure,
                    intensity = intensity
                )
            }
    }
}