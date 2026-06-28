package com.example.logicore.features.auth.navigation

sealed class AppDestination(
    val route: String
) {

    object Login : AppDestination("login")

    object AdminDashboard :
        AppDestination("admin_dashboard")

    object DispatcherDashboard :
        AppDestination("dispatcher_dashboard")

    object WarehouseDashboard :
        AppDestination("warehouse_dashboard")

    object DriverDashboard :
        AppDestination("driver_dashboard")

    object AnalystDashboard :
        AppDestination("analyst_dashboard")
}