package com.example.logicore.features.dispatch.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RealtimeDispatchScreen(
    viewModel: RealtimeDispatchViewModel
) {

    val events by viewModel.events.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text("REAL TIME DISPATCH CONTROL TOWER")

        Spacer(Modifier.height(10.dp))

        Button(onClick = {
            viewModel.refresh(vehicleId = 1)
        }) {
            Text("Refresh Status")
        }

        Spacer(Modifier.height(10.dp))

        LazyColumn {
            items(events) { event ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(6.dp)
                ) {

                    Column(Modifier.padding(12.dp)) {

                        Text("Vehicle: ${event.vehicleId}")
                        Text("Type: ${event.type}")
                        Text("Message: ${event.message}")
                    }
                }
            }
        }
    }
}