package com.example.logicore.features.tenant.core

class TenantGuard(
    private val tenantContext: TenantContext
) {

    fun <T> validateAccess(entityTenantId: String, data: T): T? {

        val current = tenantContext.getTenant()

        return if (current == entityTenantId) data else null
    }

    fun requireTenant(): String {
        return tenantContext.getTenant()
            ?: throw IllegalStateException("No tenant selected")
    }
}