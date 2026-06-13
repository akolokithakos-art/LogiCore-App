package com.example.logicore.routing.engine

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.URL

class OsrmRoutingEngine(
    private val baseUrl: String = "https://router.project-osrm.org"
) : RoutingEngine {

    override suspend fun getRoute(
        fromLat: Double,
        fromLng: Double,
        toLat: Double,
        toLng: Double
    ): RouteResult = withContext(Dispatchers.IO) {

        val url = "$baseUrl/route/v1/driving/" +
                "$fromLng,$fromLat;$toLng,$toLat?overview=false"

        val response = URL(url).readText()
        val json = JSONObject(response)

        val route = json
            .getJSONArray("routes")
            .getJSONObject(0)

        RouteResult(
            distanceMeters = route.getDouble("distance"),
            durationSeconds = route.getDouble("duration")
        )
    }
}