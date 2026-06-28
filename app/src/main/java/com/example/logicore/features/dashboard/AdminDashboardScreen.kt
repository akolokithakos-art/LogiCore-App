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
fun AdminDashboardScreen(
    onNavigate: (String) -> Unit = {}
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("ADMIN DASHBOARD") }
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
                text = "System Overview",
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(Modifier.height(16.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                onClick = { onNavigate("control_center") }
            ) {
                Column(Modifier.padding(16.dp)) {

                    Text("• Users Management")
                    Text("• Tenant Overview")
                    Text("• Fleet Status")
                    Text("• Dispatch Control")
                    Text("• System Analytics")
                }
            }

            Spacer(Modifier.height(16.dp))

            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(Modifier.padding(16.dp)) {

                    Text("Quick Actions")
                    Text("• Create User")
                    Text("• View Reports")
                    Text("• System Settings")
                }
            }
        }
    }
}