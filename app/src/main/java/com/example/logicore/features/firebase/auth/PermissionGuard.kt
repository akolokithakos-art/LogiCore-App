package com.example.logicore.features.firebase.auth

class PermissionGuard(
    private val sessionResolver: SessionResolver
) {

    suspend fun requireRole(
        role: UserRole
    ) {

        val currentRole =
            sessionResolver.role()

        require(
            currentRole == role
        ) {
            "Permission denied"
        }
    }
}