package com.example.logicore.features.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.logicore.features.dashboard.presentation.DriverDashboardViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DriverDashboardScreen(
    viewModel: DriverDashboardViewModel,
    onNavigate: (String) -> Unit = {}
) {
    val assignedRoute by viewModel.assignedRoute.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("DRIVER DASHBOARD (X-VAN)") }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {

            Text(
                text = "TODAY'S ROUTE",
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(Modifier.height(16.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                onClick = { onNavigate("van_inventory") }
            ) {

                Column(Modifier.padding(16.dp)) {

                    if (assignedRoute == null) {
                        Text("🚚 Vehicle Status: ACTIVE")
                        Text("📍 Next Stop: No Route Assigned")
                        Text("⏱ ETA: --")
                        Text("📦 Remaining Stops: 0")
                        Text("📊 Load: 0%")
                    } else {
                        Text("🚚 Vehicle Status: ACTIVE")
                        Text("📍 Next Stop: ${assignedRoute?.routePoints?.getOrNull(0) ?: "N/A"}")
                        Text("📦 Total Stops: ${assignedRoute?.routePoints?.size ?: 0}")
                        
                        Spacer(Modifier.height(8.dp))
                        Text("Πρόγραμμα Διαδρομής:", style = MaterialTheme.typography.labelMedium)
                        assignedRoute?.routePoints?.forEachIndexed { index, point ->
                            Text("${index + 1}. $point", style = MaterialTheme.typography.bodySmall)
                        }
                    }
                }
            }

            Spacer(Modifier.height(16.dp))

            Card(
                modifier = Modifier.fillMaxWidth()
            ) {

                Column(Modifier.padding(16.dp)) {

                    Text("Actions")

                    Text("• Start Route")
                    Text("• Confirm Delivery")
                    Text("• Scan Package")
                    Text("• Call Dispatcher")
                }
            }
        }
    }
}
