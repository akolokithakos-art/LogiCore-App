package com.example.logicore.features.firebase.auth

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FirestoreAuthManager(
    private val firebaseAuth: FirebaseAuth,
    private val profileRepository: UserProfileRepository
) : FirebaseAuthManager {

    private val _authState =
        MutableStateFlow<AuthState>(
            AuthState.Loading
        )

    override val authState: StateFlow<AuthState>
        get() = _authState

    override suspend fun signIn(
        email: String,
        password: String
    ) {
        TODO()
    }

    override suspend fun signOut() {
        firebaseAuth.signOut()
        _authState.value =
            AuthState.LoggedOut
    }

    override suspend fun currentSession(): FirebaseSession? {

        val user =
            firebaseAuth.currentUser
                ?: return null

        val profile =
            profileRepository.getProfile(
                user.uid
            )
                ?: return null

        return FirebaseSession(
            uid = profile.uid,
            tenantId = profile.tenantId,
            role = profile.role
        )
    }
}