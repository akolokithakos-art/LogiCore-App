package com.example.logicore.core.security

class SecurityFacade(
    private val permissionGuard: PermissionGuard,
    private val tenantGuard: TenantGuard
) {

    suspend fun requirePermission(
        permission: Permission
    ) {
        permissionGuard.require(permission)
    }

    suspend fun requireTenant(
        tenantId: String
    ) {
        tenantGuard.requireTenant(tenantId)
    }
}