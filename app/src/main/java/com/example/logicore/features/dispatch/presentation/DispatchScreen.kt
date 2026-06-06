package com.example.logicore.features.dispatch.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DispatchScreen(
    viewModel: DispatchViewModel
) {

    val plan by viewModel.plan.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text("PREDICTIVE DISPATCH")

        Spacer(Modifier.height(10.dp))

        Button(onClick = {
            viewModel.generatePlan()
        }) {
            Text("Generate Dispatch Plan")
        }

        Spacer(Modifier.height(10.dp))

        LazyColumn {
            items(plan) { item ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(6.dp)
                ) {

                    Column(Modifier.padding(12.dp)) {

                        Text("Vehicle: ${item.vehicleId}")
                        Text("Product: ${item.productId}")
                        Text("Zone: ${item.zoneId ?: "N/A"}")
                        Text("Load: ${item.recommendedLoad}")
                        Text("Priority: ${item.priority}")
                    }
                }
            }
        }
    }
}