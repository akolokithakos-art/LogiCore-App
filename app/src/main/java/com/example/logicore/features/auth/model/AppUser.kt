package com.example.logicore.features.auth.model

data class AppUser(

    val userId: String,

    val name: String,

    val role: UserRole,

    val vehicleId: Int? = null
)