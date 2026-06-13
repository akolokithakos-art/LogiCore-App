package com.example.logicore.features.firebase.conflict

import com.example.logicore.features.firebase.model.RemoteDispatchState

class ConflictResolver {

    fun resolve(
        local: RemoteDispatchState,
        remote: RemoteDispatchState
    ): RemoteDispatchState {

        // RULE 1: latest wins for GPS
        val lastSyncWinner =
            if (local.lastSync > remote.lastSync) local else remote

        // RULE 2: capacity must be minimum (safety rule)
        val capacity =
            minOf(local.remainingCapacity, remote.remainingCapacity)

        return lastSyncWinner.copy(
            remainingCapacity = capacity
        )
    }
}