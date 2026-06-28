package com.example.logicore.features.firebase.repository

import com.example.logicore.features.firebase.model.RemoteRoutePlan
import kotlinx.coroutines.flow.Flow

interface FirebaseRouteRepository {

    suspend fun saveRoute(route: RemoteRoutePlan)

    fun observeRoute(tenantId: String, driverId: String): Flow<RemoteRoutePlan?>

    suspend fun deleteRoute(tenantId: String, driverId: String)
}
