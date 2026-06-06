package com.example.logicore.features.stock.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AutoLoadScreen(
    viewModel: AutoLoadViewModel
) {

    val plan by viewModel.plan.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text("AUTO LOAD OPTIMIZER")

        Spacer(Modifier.height(10.dp))

        Button(onClick = {
            viewModel.generate()
        }) {
            Text("Generate Load Plan")
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

                        Text("Product: ${item.productId}")
                        Text("Current: ${item.currentStock}")
                        Text("Avg/day: ${item.avgDailyDemand}")
                        Text("RECOMMENDED LOAD: ${item.suggestedQty}")

                    }
                }
            }
        }
    }
}