package com.example.logicore.features.routing.domain

data class RouteScore(

    val distanceKm: Double,

    val etaMinutes: Double,

    val trafficMultiplier: Double,

    val riskScore: Double
)