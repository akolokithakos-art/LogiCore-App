package com.example.logicore.features.firebase.sync

import com.example.logicore.features.firebase.event.DispatchEventBus
import com.example.logicore.features.firebase.model.RemoteVehicleState
import com.example.logicore.features.firebase.repository.FirebaseDispatchRepository

class FirebaseSyncManager(
    private val repository: FirebaseDispatchRepository,
    private val eventBus: DispatchEventBus
) {

    suspend fun push(
        state: RemoteVehicleState
    ) {

        repository.saveVehicleState(state)

        eventBus.publish(state)
    }
}