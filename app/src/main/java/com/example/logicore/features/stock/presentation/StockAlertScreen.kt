package com.example.logicore.features.stock.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun StockAlertScreen(
    viewModel: StockAlertViewModel
) {

    val alerts by viewModel.alerts.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.refresh()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text("STOCK ALERTS")

        Spacer(Modifier.height(10.dp))

        LazyColumn {
            items(alerts) { alert ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(6.dp)
                ) {
                    Column(Modifier.padding(12.dp)) {

                        Text("Product: ${alert.productId}")

                        Text("Stock: ${alert.currentStock}")

                        Text("Need: ${alert.recommendedQty}")

                        Text(
                            text = alert.severity,
                            color = when (alert.severity) {
                                "CRITICAL" -> MaterialTheme.colorScheme.error
                                else -> MaterialTheme.colorScheme.primary
                            }
                        )
                    }
                }
            }
        }
    }
}