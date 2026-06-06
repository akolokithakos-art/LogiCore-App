package com.example.logicore.features.stock.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun VanInventoryScreen(
    viewModel: VanInventoryViewModel
) {

    val stock by viewModel.stock.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "VAN INVENTORY",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(12.dp))

        LazyColumn {
            items(stock) { item ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text("Product ID: ${item.productId}")
                        Text("Name: ${item.productName}")
                        Text("Qty: ${item.quantity}")
                    }
                }
            }
        }
    }
}