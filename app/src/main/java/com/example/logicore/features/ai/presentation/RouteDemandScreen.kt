package com.example.logicore.features.ai.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RouteDemandScreen(
    viewModel: RouteDemandViewModel
) {

    val data by viewModel.result.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text("ROUTE DEMAND AI")

        Spacer(Modifier.height(10.dp))

        Button(onClick = {
            viewModel.runAnalysis()
        }) {
            Text("Analyze Route Demand")
        }

        Spacer(Modifier.height(10.dp))

        LazyColumn {
            items(data) { item ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(6.dp)
                ) {

                    Column(Modifier.padding(12.dp)) {

                        Text("Zone: ${item.zoneId}")
                        Text("Product: ${item.productId}")
                        Text("Avg Demand: ${item.avgDemand}")
                        Text("Recommended Load: ${item.recommendedLoad}")
                    }
                }
            }
        }
    }
}