
package com.example.logicore.features.firebase.auth

class SessionResolver(
    private val authManager: FirebaseAuthManager
) {

    suspend fun tenantId(): String? {
        return authManager
            .currentSession()
            ?.tenantId
    }

    suspend fun role(): UserRole? {
        return authManager
            .currentSession()
            ?.role
    }
}