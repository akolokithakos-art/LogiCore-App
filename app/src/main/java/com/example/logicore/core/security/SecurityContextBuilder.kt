package com.example.logicore.core.security

import com.example.logicore.features.firebase.auth.FirebaseUserProfile

class SecurityContextBuilder(
    private val permissionService: PermissionService
) {

    fun build(
        profile: FirebaseUserProfile
    ): SecurityContext {

        val permissions =
            RolePermissions.permissions(profile.role)

        return SecurityContext(
            uid = profile.uid,
            tenantId = profile.tenantId,
            role = profile.role,
            permissions = permissions
        )
    }
}