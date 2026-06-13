package com.example.logicore.features.auth.security

import com.example.logicore.features.auth.model.UserRole

class PermissionEngine {

    fun canAccessDashboard(role: UserRole): Boolean {
        return role != UserRole.DRIVER
    }

    fun canExecuteDispatch(role: UserRole): Boolean {
        return role == UserRole.ADMIN || role == UserRole.DISPATCHER
    }

    fun canModifyStock(role: UserRole): Boolean {
        return role == UserRole.ADMIN || role == UserRole.WAREHOUSE
    }

    fun canViewSales(role: UserRole): Boolean {
        return true
    }

    fun canOverrideAI(role: UserRole): Boolean {
        return role == UserRole.ADMIN
    }
}