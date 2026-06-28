package com.example.logicore.core.security

fun SecurityContext.hasPermission(
    permission: Permission
): Boolean {

    return permissions.contains(permission)
}