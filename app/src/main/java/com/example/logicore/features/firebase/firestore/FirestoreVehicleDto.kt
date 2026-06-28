package com.example.logicore.features.firebase.firestore

data class FirestoreVehicleDto(

    val tenantId: String = "",

    val vehicleId: Int = 0,

    val driverId: Int? = null,

    val lastSync: Long = 0L,

    val latitude: Double = 0.0,

    val longitude: Double = 0.0,

    val speedKmh: Double = 0.0,

    val heading: Double? = null,

    val accuracyMeters: Float? = null,

    val totalCapacity: Double = 0.0,

    val remainingCapacity: Double = 0.0,

    val routeId: Int? = null,

    val orderId: Int? = null,

    val status: String = "AVAILABLE"
)