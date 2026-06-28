package com.example.logicore.features.firebase.firestore

import com.google.firebase.firestore.FirebaseFirestore
import com.example.logicore.features.firebase.model.RemoteVehicleState
import com.example.logicore.features.firebase.path.FirebasePathBuilder
import com.example.logicore.features.firebase.repository.FirebaseDispatchRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class FirestoreFirebaseDispatchRepository(
    private val firestore: FirebaseFirestore
) : FirebaseDispatchRepository {

    override suspend fun saveVehicleState(
        state: RemoteVehicleState
    ) {

        val path =
            FirebasePathBuilder.vehiclePath(
                state.metadata.tenantId,
                state.metadata.vehicleId
            )

        firestore
            .document(path)
            .set(
                FirestoreVehicleMapper.toMap(state)
            )
            .await()
    }

    override fun observeVehicleState(
        tenantId: String,
        vehicleId: Int
    ): Flow<RemoteVehicleState?> = callbackFlow {

        val path =
            FirebasePathBuilder.vehiclePath(
                tenantId,
                vehicleId
            )

        val registration =
            firestore
                .document(path)
                .addSnapshotListener { snapshot, _ ->

                    if (snapshot == null) {
                        trySend(null)
                        return@addSnapshotListener
                    }

                    val dto =
                        snapshot.toObject(
                            FirestoreVehicleDto::class.java
                        )

                    if (dto == null) {
                        trySend(null)
                        return@addSnapshotListener
                    }

                    trySend(
                        FirestoreVehicleDtoMapper
                            .toDomain(dto)
                    )
                }

        awaitClose {
            registration.remove()
        }
    }

    override suspend fun deleteVehicleState(
        tenantId: String,
        vehicleId: Int
    ) {

        val path =
            FirebasePathBuilder.vehiclePath(
                tenantId,
                vehicleId
            )

        firestore
            .document(path)
            .delete()
            .await()
    }
}