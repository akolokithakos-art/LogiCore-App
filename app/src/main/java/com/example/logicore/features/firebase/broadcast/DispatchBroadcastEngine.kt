package com.example.logicore.features.firebase.broadcast

import com.example.logicore.features.firebase.model.RemoteVehicleState
import java.util.concurrent.CopyOnWriteArrayList

class DispatchBroadcastEngine {

    private val listeners =
        CopyOnWriteArrayList<(RemoteVehicleState) -> Unit>()

    fun subscribe(
        listener: (RemoteVehicleState) -> Unit
    ) {
        listeners.add(listener)
    }

    fun unsubscribe(
        listener: (RemoteVehicleState) -> Unit
    ) {
        listeners.remove(listener)
    }

    fun clear() {
        listeners.clear()
    }

    fun emit(
        state: RemoteVehicleState
    ) {
        listeners.forEach { listener ->
            listener(state)
        }
    }

    fun listenerCount(): Int {
        return listeners.size
    }
}