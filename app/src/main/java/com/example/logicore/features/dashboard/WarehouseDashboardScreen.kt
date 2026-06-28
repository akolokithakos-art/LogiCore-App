package com.example.logicore.features.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import kotlin.OptIn

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WarehouseDashboardScreen(
    onNavigate: (String) -> Unit = {}
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("WAREHOUSE DASHBOARD") }
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
                text = "WAREHOUSE OPERATIONS",
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(Modifier.height(16.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                onClick = { onNavigate("stock_alerts") }
            ) {

                Column(Modifier.padding(16.dp)) {

                    Text("📦 Inventory Status")
                    Text("📥 Incoming Shipments")
                    Text("📤 Outgoing Orders")
                    Text("🚚 Loading Queue")
                    Text("⚠️ Stock Alerts")
                }
            }

            Spacer(Modifier.height(16.dp))

            Card(
                modifier = Modifier.fillMaxWidth()
            ) {

                Column(Modifier.padding(16.dp)) {

                    Text("Quick Actions")
                    Text("• Start Picking")
                    Text("• Start Packing")
                    Text("• Assign Loading Bay")
                }
            }
        }
    }
}