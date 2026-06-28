package com.example.logicore.features.firebase.auth

import kotlinx.coroutines.flow.Flow

interface FirebaseAuthManager {

    val authState: Flow<AuthState>

    suspend fun signIn(
        email: String,
        password: String
    )

    suspend fun signOut()

    suspend fun currentSession(): FirebaseSession?
}