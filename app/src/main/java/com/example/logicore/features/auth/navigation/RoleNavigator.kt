package com.example.logicore.features.auth.navigation

import com.example.logicore.features.auth.model.UserRole

object RoleNavigator {

    fun dashboardFor(
        role: UserRole
    ): String {

        return when (role) {

            UserRole.ADMIN ->
                AppDestination.AdminDashboard.route

            UserRole.DISPATCHER ->
                AppDestination.DispatcherDashboard.route

            UserRole.WAREHOUSE ->
                AppDestination.WarehouseDashboard.route

            UserRole.DRIVER ->
                AppDestination.DriverDashboard.route

            UserRole.ANALYST ->
                AppDestination.AnalystDashboard.route
        }
    }
}