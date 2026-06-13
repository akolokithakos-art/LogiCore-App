package com.example.logicore.features.firebase.sync

import com.example.logicore.features.firebase.model.RemoteDispatchState

class FirebaseDispatchSyncEngine {

    // private val db = FirebaseFirestore.getInstance()

    fun pushState(state: RemoteDispatchState) {

        // db.collection("tenants")
        //     .document(state.tenantId)
        //     .collection("dispatch_states")
        //     .document(state.vehicleId.toString())
        //     .set(state)

    }

    fun subscribe(
        tenantId: String,
        onUpdate: (RemoteDispatchState) -> Unit
    ) {

        // db.collection("tenants")
        //     .document(tenantId)
        //     .collection("dispatch_states")
        //     .addSnapshotListener { snapshot, _ ->
        //         snapshot?.toObjects(RemoteDispatchState::class.java)
        //             ?.forEach { onUpdate(it) }
        //     }
    }
}