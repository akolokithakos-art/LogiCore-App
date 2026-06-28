package com.example.logicore.features.firebase.event

import com.example.logicore.features.firebase.model.RemoteVehicleState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class DispatchEventBus {

    private val updates =
        MutableSharedFlow<RemoteVehicleState>(
            replay = 1,
            extraBufferCapacity = 64
        )

    suspend fun publish(
        state: RemoteVehicleState
    ) {
        updates.emit(state)
    }

    fun stream(): SharedFlow<RemoteVehicleState> {
        return updates.asSharedFlow()
    }
}