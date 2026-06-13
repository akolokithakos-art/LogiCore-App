package com.logicore.tracking.engine

import com.example.logicore.dispatch.engine.DriverStateTracker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class DriverLiveSync(
    private val locationTracker: LocationTracker,
    private val driverStateTracker: DriverStateTracker,
    private val scope: CoroutineScope
) {

    fun start(driverId: String): Flow<Pair<Double, Double>> {

        val flow = locationTracker.locationFlow()

        scope.launch {
            flow.collect { (lat, lng) ->
                driverStateTracker.updatePosition(driverId, lat, lng)
            }
        }

        return flow
    }
}