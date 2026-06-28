package com.example.logicore.features.firebase.sync

import com.example.logicore.features.firebase.model.RemoteVehicleState

class FirebaseDispatchSyncEngine {

    // private val db = FirebaseFirestore.getInstance()

    fun pushState(state: RemoteVehicleState) {

        // db.collection("tenants")
        //     .document(state.tenantId)
        //     .collection("dispatch_states")
        //     .document(state.vehicleId.toString())
        //     .set(state)

    }

    fun subscribe(
        tenantId: String,
        onUpdate: (RemoteVehicleState) -> Unit
    ) {

        // db.collection("tenants")
        //     .document(tenantId)
        //     .collection("dispatch_states")
        //     .addSnapshotListener { snapshot, _ ->
        //         snapshot?.toObjects(RemoteVehicleState::class.java)
        //             ?.forEach { onUpdate(it) }
        //     }
    }
}