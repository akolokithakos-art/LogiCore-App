package com.example.logicore.features.firebase.path

object FirebasePathBuilder {

    fun vehiclePath(
        tenantId: String,
        vehicleId: Int
    ): String = "tenants/$tenantId/fleet/$vehicleId"
}