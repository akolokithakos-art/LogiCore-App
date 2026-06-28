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
fun AnalystDashboardScreen(
    onNavigate: (String) -> Unit = {}
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("ANALYST DASHBOARD") }
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
                text = "AI & BUSINESS INTELLIGENCE",
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(16.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                onClick = { onNavigate("route_demand") }
            ) {

                Column(
                    modifier = Modifier.padding(16.dp)
                ) {

                    Text("📊 Revenue Analytics")
                    Text("📈 Demand Forecasting")
                    Text("🚚 Fleet Utilization Analysis")
                    Text("⚠️ SLA Risk Monitoring")
                    Text("🧠 AI Route Optimization Insights")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Card(
                modifier = Modifier.fillMaxWidth()
            ) {

                Column(
                    modifier = Modifier.padding(16.dp)
                ) {

                    Text("Quick Reports")
                    Text("• Daily Performance")
                    Text("• Driver Efficiency")
                    Text("• Delivery Heatmaps")
                }
            }
        }
    }
}