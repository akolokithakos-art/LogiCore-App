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
fun DispatcherDashboardScreen(
    onNavigate: (String) -> Unit = {}
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("DISPATCHER DASHBOARD") }
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
                text = "LIVE DISPATCH CONTROL CENTER",
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(Modifier.height(16.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                onClick = { onNavigate("dispatch") }
            ) {

                Column(Modifier.padding(16.dp)) {

                    Text("🚚 Active Routes")
                    Text("📍 Live Vehicle Tracking")
                    Text("📦 Unassigned Orders")
                    Text("⚠️ SLA Alerts")
                    Text("🧠 AI Dispatch Suggestions")
                }
            }

            Spacer(Modifier.height(16.dp))

            Card(
                modifier = Modifier.fillMaxWidth()
            ) {

                Column(Modifier.padding(16.dp)) {

                    Text("Quick Actions")
                    TextButton(onClick = { onNavigate("add_address") }) {
                        Text("• Εισαγωγή Διεύθυνσης")
                    }
                    TextButton(onClick = { onNavigate("add_address") }) {
                        Text("• Δημιουργία Δρομολογίου")
                    }
                    Text("• Reassign Route")
                    Text("• Force Dispatch")
                    Text("• Pause Driver")
                }
            }
        }
    }
}