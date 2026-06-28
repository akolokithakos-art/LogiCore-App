package com.example.logicore.features.firebase.firestore

import com.example.logicore.features.firebase.model.RemoteRoutePlan
import com.example.logicore.features.firebase.repository.FirebaseRouteRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class FirestoreFirebaseRouteRepository(
    private val firestore: FirebaseFirestore
) : FirebaseRouteRepository {

    override suspend fun saveRoute(route: RemoteRoutePlan) {
        val path = "tenants/${route.tenantId}/routes/${route.driverId}"
        firestore.document(path).set(route).await()
    }

    override fun observeRoute(tenantId: String, driverId: String): Flow<RemoteRoutePlan?> = callbackFlow {
        val path = "tenants/$tenantId/routes/$driverId"
        val registration = firestore.document(path).addSnapshotListener { snapshot, _ ->
            if (snapshot != null && snapshot.exists()) {
                trySend(snapshot.toObject(RemoteRoutePlan::class.java))
            } else {
                trySend(null)
            }
        }
        awaitClose { registration.remove() }
    }

    override suspend fun deleteRoute(tenantId: String, driverId: String) {
        val path = "tenants/$tenantId/routes/$driverId"
        firestore.document(path).delete().await()
    }
}
