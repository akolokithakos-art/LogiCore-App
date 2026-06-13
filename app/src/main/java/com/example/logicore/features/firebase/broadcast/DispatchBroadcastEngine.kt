package com.example.logicore.features.firebase.broadcast

import com.example.logicore.features.firebase.model.RemoteDispatchState

class DispatchBroadcastEngine {

    private val listeners =
        mutableListOf<(RemoteDispatchState) -> Unit>()

    fun subscribe(listener: (RemoteDispatchState) -> Unit) {
        listeners.add(listener)
    }

    fun emit(state: RemoteDispatchState) {

        listeners.forEach {
            it(state)
        }
    }
}