package com.example.logicore.features.firebase.repository

import com.example.logicore.features.firebase.model.RemoteVehicleState
import kotlinx.coroutines.flow.Flow

interface FirebaseDispatchRepository {

    suspend fun saveVehicleState(
        state: RemoteVehicleState
    )

    fun observeVehicleState(
        tenantId: String,
        vehicleId: Int
    ): Flow<RemoteVehicleState?>

    suspend fun deleteVehicleState(
        tenantId: String,
        vehicleId: Int
    )
}