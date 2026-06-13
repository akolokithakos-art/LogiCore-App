package com.example.logicore.features.dashboard.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.logicore.features.dashboard.presentation.ControlCenterViewModel

@Composable
fun ControlCenterScreen(vm: ControlCenterViewModel) {

    val state by vm.state.collectAsState()

    Column(modifier = Modifier.fillMaxSize().padding(12.dp)) {

        Text("LOGICORE CONTROL CENTER", style = MaterialTheme.typography.titleLarge)

        Spacer(Modifier.height(12.dp))

        Row(Modifier.fillMaxSize()) {

            // LEFT PANEL - ALERTS
            Card(Modifier.weight(1f).fillMaxHeight().padding(4.dp)) {
                LazyColumn {
                    items(state.alerts.size) { i ->
                        Text("⚠️ ${state.alerts[i]}", modifier = Modifier.padding(8.dp))
                    }
                }
            }

            // MIDDLE PANEL - AI DECISIONS
            Card(Modifier.weight(1f).fillMaxHeight().padding(4.dp)) {
                LazyColumn {
                    items(state.aiDecisions.size) { i ->
                        Text("🧠 ${state.aiDecisions[i]}", modifier = Modifier.padding(8.dp))
                    }
                }
            }

            // RIGHT PANEL - SYSTEM STATUS
            Card(Modifier.weight(1f).fillMaxHeight().padding(4.dp)) {
                Column(Modifier.padding(8.dp)) {

                    Text("SYSTEM STATUS")

                    Text("Vehicles: ${state.vehicles.size}")
                    Text("Sales: ${state.sales.size}")
                }
            }
        }
    }
}