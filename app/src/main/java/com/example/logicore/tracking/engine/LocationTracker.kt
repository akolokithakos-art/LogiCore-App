package com.logicore.tracking.engine

import android.annotation.SuppressLint
import android.content.Context
import com.google.android.gms.location.*
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class LocationTracker(
    context: Context
) {

    private val client = LocationServices.getFusedLocationProviderClient(context)

    @SuppressLint("MissingPermission")
    fun locationFlow(): Flow<Pair<Double, Double>> = callbackFlow {

        val request = LocationRequest.create().apply {
            interval = 3000
            fastestInterval = 2000
            priority = Priority.PRIORITY_HIGH_ACCURACY
        }

        val callback = object : LocationCallback() {
            override fun onLocationResult(result: LocationResult) {
                val loc = result.lastLocation ?: return
                trySend(loc.latitude to loc.longitude)
            }
        }

        client.requestLocationUpdates(request, callback, null)

        awaitClose {
            client.removeLocationUpdates(callback)
        }
    }
}