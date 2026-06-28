package com.example.logicore.features.firebase.firestore

import com.example.logicore.features.firebase.model.*

object FirestoreVehicleMapper {

    fun toMap(
        state: RemoteVehicleState
    ): Map<String, Any?> {

        return mapOf(

            "tenantId" to state.metadata.tenantId,
            "vehicleId" to state.metadata.vehicleId,
            "driverId" to state.metadata.driverId,
            "lastSync" to state.metadata.lastSync,

            "latitude" to state.position.latitude,
            "longitude" to state.position.longitude,
            "speedKmh" to state.position.speedKmh,
            "heading" to state.position.heading,
            "accuracyMeters" to state.position.accuracyMeters,

            "totalCapacity" to state.capacity.totalCapacity,
            "remainingCapacity" to state.capacity.remainingCapacity,

            "routeId" to state.assignment?.routeId,
            "orderId" to state.assignment?.orderId,

            "status" to state.status.name
        )
    }
}