package com.example.logicore.core.security

import com.example.logicore.features.firebase.auth.UserRole

object RolePermissions {

    fun permissions(
        role: UserRole
    ): Set<Permission> {

        return when (role) {

            UserRole.SUPER_ADMIN ->
                Permission.entries.toSet()

            UserRole.TENANT_ADMIN ->
                Permission.entries
                    .filter {
                        it != Permission.SYSTEM_ADMIN
                    }
                    .toSet()

            UserRole.DISPATCHER ->
                setOf(
                    Permission.VIEW_ORDERS,
                    Permission.UPDATE_ORDERS,
                    Permission.VIEW_DISPATCH,
                    Permission.EXECUTE_DISPATCH,
                    Permission.VIEW_DRIVERS,
                    Permission.VIEW_VEHICLES
                )

            UserRole.DRIVER ->
                setOf(
                    Permission.VIEW_ORDERS
                )

            UserRole.WAREHOUSE ->
                setOf(
                    Permission.VIEW_WAREHOUSE,
                    Permission.EXECUTE_PICKING,
                    Permission.EXECUTE_LOADING
                )

            UserRole.MANAGER ->
                setOf(
                    Permission.VIEW_REPORTS,
                    Permission.VIEW_ORDERS,
                    Permission.VIEW_CUSTOMERS,
                    Permission.VIEW_DISPATCH
                )

            UserRole.VIEWER ->
                emptySet()
        }
    }
}