package com.example.logicore.features.dispatch.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddAddressScreen(
    viewModel: ManualRoutingViewModel,
    onNavigateToRoute: () -> Unit
) {
    var newAddress by remember { mutableStateOf("") }
    val addresses by viewModel.addresses.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Εισαγωγή Διευθύνσεων") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                TextField(
                    value = newAddress,
                    onValueChange = { newAddress = it },
                    label = { Text("Διεύθυνση") },
                    modifier = Modifier.weight(1f)
                )
                IconButton(onClick = {
                    viewModel.addAddress(newAddress)
                    newAddress = ""
                }) {
                    Icon(Icons.Default.Add, contentDescription = "Add")
                }
            }

            Spacer(Modifier.height(16.dp))

            LazyColumn(modifier = Modifier.weight(1f)) {
                items(addresses) { addr ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(addr, modifier = Modifier.weight(1f))
                            IconButton(onClick = { viewModel.removeAddress(addr) }) {
                                Icon(Icons.Default.Delete, contentDescription = "Delete")
                            }
                        }
                    }
                }
            }

            Button(
                onClick = {
                    viewModel.createRoute()
                    onNavigateToRoute()
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = addresses.isNotEmpty()
            ) {
                Text("Δημιουργία Δρομολογίου")
            }
        }
    }
}
