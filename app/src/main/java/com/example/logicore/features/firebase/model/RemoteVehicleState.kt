package com.example.logicore.features.firebase.model

data class RemoteVehicleState(

    val metadata: SyncMetadata,

    val position: VehiclePosition,

    val capacity: VehicleCapacity,

    val assignment: DispatchAssignment? = null,

    val status: VehicleStatus = VehicleStatus.AVAILABLE
)