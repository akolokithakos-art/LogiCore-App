package com.example.logicore.features.firebase.auth

data class FirebaseSession(

    val uid: String,

    val tenantId: String,

    val role: UserRole
)