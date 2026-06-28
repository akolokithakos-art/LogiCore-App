package com.example.logicore.navigation

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.compose.ui.platform.LocalContext
import com.example.logicore.ServiceLocator
import com.example.logicore.features.auth.LoginScreen
import com.example.logicore.features.auth.model.UserRole
import com.example.logicore.features.dashboard.*
import com.example.logicore.features.dispatch.presentation.*
import com.example.logicore.features.ai.presentation.*
import com.example.logicore.features.stock.presentation.*
import com.example.logicore.features.dashboard.ui.ControlCenterScreen
import com.example.logicore.features.dashboard.presentation.ControlCenterViewModel
import com.example.logicore.features.dashboard.presentation.DriverDashboardViewModel
import com.example.logicore.features.address.domain.NominatimGeocoder
import com.example.logicore.routing.engine.OsrmRoutingEngine

@Composable
fun AppNavGraph() {

    val navController = rememberNavController()
    val context = LocalContext.current
    val application = context.applicationContext as Application
    
    // Manual Routing ViewModel with Dependencies
    val manualRoutingViewModel: ManualRoutingViewModel = viewModel(
        factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return ManualRoutingViewModel(
                    application = application,
                    geocoder = NominatimGeocoder(),
                    routingEngine = OsrmRoutingEngine(),
                    driverDao = ServiceLocator.driverDao(context),
                    routeRepository = ServiceLocator.firebaseRouteRepository()
                ) as T
            }
        }
    )

    val driverDashboardViewModel: DriverDashboardViewModel = viewModel(
        factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return DriverDashboardViewModel(
                    routeRepository = ServiceLocator.firebaseRouteRepository(),
                    driverId = "driver-1" // Mocked
                ) as T
            }
        }
    )

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {

        composable("login") {
            LoginScreen(
                onLoginSuccess = { role ->
                    val destination = when (role) {
                        UserRole.ADMIN -> "admin_dashboard"
                        UserRole.DISPATCHER -> "dispatcher_dashboard"
                        UserRole.DRIVER -> "driver_dashboard"
                        UserRole.WAREHOUSE -> "warehouse_dashboard"
                        UserRole.ANALYST -> "analyst_dashboard"
                    }
                    navController.navigate(destination) {
                        popUpTo("login") { inclusive = true }
                    }
                }
            )
        }

        composable("admin_dashboard") {
            AdminDashboardScreen(onNavigate = { navController.navigate(it) })
        }

        composable("dispatcher_dashboard") {
            DispatcherDashboardScreen(onNavigate = { navController.navigate(it) })
        }

        composable("warehouse_dashboard") {
            WarehouseDashboardScreen(onNavigate = { navController.navigate(it) })
        }

        composable("driver_dashboard") {
            DriverDashboardScreen(
                viewModel = driverDashboardViewModel,
                onNavigate = { navController.navigate(it) }
            )
        }

        composable("analyst_dashboard") {
            AnalystDashboardScreen(onNavigate = { navController.navigate(it) })
        }

        composable("dispatch") {
            DispatchScreen(viewModel = viewModel())
        }

        composable("route_demand") {
            RouteDemandScreen(viewModel = viewModel())
        }

        composable("van_inventory") {
            VanInventoryScreen(viewModel = viewModel())
        }

        composable("stock_alerts") {
            StockAlertScreen(viewModel = viewModel())
        }

        composable("control_center") {
            ControlCenterScreen(vm = viewModel())
        }

        composable("add_address") {
            AddAddressScreen(
                viewModel = manualRoutingViewModel,
                onNavigateToRoute = { navController.navigate("route_result") }
            )
        }

        composable("route_result") {
            RouteResultScreen(viewModel = manualRoutingViewModel)
        }
    }
}
