package com.example.logicore.features.firebase.firestore

import com.example.logicore.features.firebase.model.*

object FirestoreVehicleDtoMapper {

    fun toDomain(
        dto: FirestoreVehicleDto
    ): RemoteVehicleState {

        return RemoteVehicleState(

            metadata =
                SyncMetadata(
                    tenantId = dto.tenantId,
                    vehicleId = dto.vehicleId,
                    driverId = dto.driverId,
                    lastSync = dto.lastSync
                ),

            position =
                VehiclePosition(
                    latitude = dto.latitude,
                    longitude = dto.longitude,
                    speedKmh = dto.speedKmh,
                    heading = dto.heading,
                    accuracyMeters = dto.accuracyMeters
                ),

            capacity =
                VehicleCapacity(
                    totalCapacity = dto.totalCapacity,
                    remainingCapacity = dto.remainingCapacity
                ),

            assignment =
                DispatchAssignment(
                    routeId = dto.routeId,
                    orderId = dto.orderId
                ),

            status =
                VehicleStatus.valueOf(dto.status)
        )
    }
}