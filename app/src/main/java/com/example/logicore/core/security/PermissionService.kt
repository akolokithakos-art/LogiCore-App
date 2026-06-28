package com.example.logicore.core.security

import com.example.logicore.features.firebase.auth.UserRole

class PermissionService {

    fun hasPermission(
        role: UserRole,
        permission: Permission
    ): Boolean {

        return RolePermissions
            .permissions(role)
            .contains(permission)
    }
}