package com.example.logicore.features.firebase.conflict

import com.example.logicore.features.firebase.model.RemoteVehicleState
import com.example.logicore.features.firebase.model.SyncMetadata

class VehicleStateConflictResolver {

    fun resolve(
        local: RemoteVehicleState,
        remote: RemoteVehicleState
    ): RemoteVehicleState {

        require(
            local.metadata.tenantId ==
                    remote.metadata.tenantId
        ) {
            "Tenant mismatch"
        }

        require(
            local.metadata.vehicleId ==
                    remote.metadata.vehicleId
        ) {
            "Vehicle mismatch"
        }

        val newest =
            if (
                local.metadata.lastSync >=
                remote.metadata.lastSync
            ) {
                local
            } else {
                remote
            }

        val safestCapacity =
            minOf(
                local.capacity.remainingCapacity,
                remote.capacity.remainingCapacity
            )

        return newest.copy(

            capacity =
                newest.capacity.copy(
                    remainingCapacity = safestCapacity
                ),

            metadata =
                SyncMetadata(
                    tenantId = newest.metadata.tenantId,
                    vehicleId = newest.metadata.vehicleId,
                    driverId = newest.metadata.driverId,
                    lastSync =
                        maxOf(
                            local.metadata.lastSync,
                            remote.metadata.lastSync
                        )
                )
        )
    }
}