package com.example.logicore.features.sales.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SalesScreen(
    viewModel: SalesViewModel,
    customers: List<com.example.logicore.features.customers.data.local.CustomerEntity>
) {

    var selectedCustomer by remember { mutableStateOf<Int?>(null) }

    Column {

        Text("SALES")

        Spacer(Modifier.height(10.dp))

        Text("Select Customer")

        customers.forEach { customer ->
            Button(onClick = {
                selectedCustomer = customer.id
                viewModel.selectCustomer(customer.id)
            }) {
                Text(customer.name)
            }
        }

        Spacer(Modifier.height(10.dp))

        Button(
            onClick = {
                viewModel.startSale()
            },
            enabled = selectedCustomer != null
        ) {
            Text("Start Sale")
        }

        Spacer(Modifier.height(10.dp))

        Button(onClick = {
            viewModel.addProduct(
                productId = 1,
                qty = 2.0,
                price = 10.0
            )
        }) {
            Text("Add Product TEST")
        }
    }
}