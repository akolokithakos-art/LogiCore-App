package com.example.logicore.core.security

class PermissionGuard(
    private val securityContextProvider: SecurityContextProvider
) {

    suspend fun require(
        permission: Permission
    ) {

        val context =
            securityContextProvider.current()
                ?: throw UnauthenticatedException(
                    "No active session"
                )

        if (!context.hasPermission(permission)) {

            throw AccessDeniedException(
                "Missing permission: $permission"
            )
        }
    }
}