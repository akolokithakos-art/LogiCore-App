package com.example.logicore.features.customers.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CustomerScreen(
    viewModel: CustomerViewModel
) {

    val customers by viewModel.customers.collectAsState()

    var name by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text("CUSTOMERS")

        Spacer(Modifier.height(10.dp))

        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") }
        )

        TextField(
            value = phone,
            onValueChange = { phone = it },
            label = { Text("Phone") }
        )

        Button(onClick = {
            viewModel.addCustomer(name, phone)
            name = ""
            phone = ""
        }) {
            Text("Add Customer")
        }

        Spacer(Modifier.height(20.dp))

        LazyColumn {
            items(customers) { c ->
                Text("${c.name} - ${c.phone}")
                Divider()
            }
        }
    }
}