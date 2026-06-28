package com.example.logicore.features.firebase.auth

sealed interface AuthState {

    data object Loading : AuthState

    data object LoggedOut : AuthState

    data class LoggedIn(
        val session: FirebaseSession
    ) : AuthState
}