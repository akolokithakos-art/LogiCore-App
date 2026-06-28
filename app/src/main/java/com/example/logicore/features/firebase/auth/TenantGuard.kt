package com.example.logicore.features.firebase.auth

class TenantGuard(
    private val sessionResolver: SessionResolver
) {

    suspend fun requireTenant(
        tenantId: String
    ) {

        val current =
            sessionResolver.tenantId()

        require(
            current == tenantId
        ) {
            "Tenant access denied"
        }
    }
}