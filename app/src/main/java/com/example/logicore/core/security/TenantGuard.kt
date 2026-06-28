package com.example.logicore.core.security

class TenantGuard(
    private val securityContextProvider: SecurityContextProvider
) {

    suspend fun requireTenant(
        tenantId: String
    ) {

        val context =
            securityContextProvider.current()
                ?: throw UnauthenticatedException(
                    "No active session"
                )

        if (context.tenantId != tenantId) {

            throw TenantViolationException(
                "Tenant mismatch"
            )
        }
    }
}