package com.example.logicore.features.firebase.auth

data class FirebaseUserProfile(

    val uid: String = "",

    val email: String? = null,

    val tenantId: String = "",

    val role: UserRole = UserRole.VIEWER,

    val active: Boolean = true,

    val createdAt: Long = 0L,

    val updatedAt: Long = 0L
)