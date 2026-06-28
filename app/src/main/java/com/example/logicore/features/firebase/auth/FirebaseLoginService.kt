package com.example.logicore.features.firebase.auth

import com.example.logicore.core.security.SecurityContextBuilder
import com.example.logicore.core.security.SecuritySessionManager
import com.example.logicore.core.security.UnauthenticatedException
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class FirebaseLoginService(
    private val auth: FirebaseAuth,
    private val profileRepo: UserProfileRepository,
    private val contextBuilder: SecurityContextBuilder,
    private val sessionManager: SecuritySessionManager
) {

    suspend fun login(
        email: String,
        password: String
    ) {

        val result =
            auth.signInWithEmailAndPassword(email, password)
                .await()

        val uid =
            result.user?.uid
                ?: throw UnauthenticatedException("Invalid login")

        val profile =
            profileRepo.getProfile(uid)
                ?: throw UnauthenticatedException("Profile not found")

        val context =
            contextBuilder.build(profile)

        sessionManager.set(context)
    }
}