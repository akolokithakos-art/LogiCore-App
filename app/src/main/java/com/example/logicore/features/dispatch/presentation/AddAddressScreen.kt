package com.example.logicore.features.dispatch.presentation

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.logicore.features.address.domain.model.DetailedAddress

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddAddressScreen(
    viewModel: ManualRoutingViewModel,
    onNavigateToRoute: () -> Unit
) {
    var street by remember { mutableStateOf("") }
    var number by remember { mutableStateOf("") }
    var area by remember { mutableStateOf("") }
    var pc by remember { mutableStateOf("") }

    val addresses by viewModel.addresses.collectAsState()
    val error by viewModel.error.collectAsState()

    val filePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let { viewModel.importFromFile(it) }
    }

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
            error?.let {
                Text(it, color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodySmall)
                Spacer(Modifier.height(8.dp))
            }

            Column(modifier = Modifier.fillMaxWidth()) {
                TextField(
                    value = street,
                    onValueChange = { street = it },
                    label = { Text("Οδός") },
                    modifier = Modifier.fillMaxWidth()
                )
                Row(modifier = Modifier.fillMaxWidth()) {
                    TextField(
                        value = number,
                        onValueChange = { number = it },
                        label = { Text("Αριθμός") },
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(Modifier.width(8.dp))
                    TextField(
                        value = area,
                        onValueChange = { area = it },
                        label = { Text("Περιοχή") },
                        modifier = Modifier.weight(2f)
                    )
                }
                TextField(
                    value = pc,
                    onValueChange = { pc = it },
                    label = { Text("Ταχυδρομικός Κώδικας (ΤΚ)") },
                    modifier = Modifier.fillMaxWidth()
                )
                
                Button(
                    onClick = {
                        viewModel.addAddress(DetailedAddress(street, number, area, pc))
                        street = ""; number = ""; area = ""; pc = ""
                    },
                    modifier = Modifier.align(Alignment.End).padding(top = 8.dp),
                    enabled = street.isNotBlank() && area.isNotBlank()
                ) {
                    Icon(Icons.Default.Add, contentDescription = null)
                    Text("Προσθήκη")
                }
            }

            Spacer(Modifier.height(8.dp))

            OutlinedButton(
                onClick = { filePickerLauncher.launch("*/*") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(Icons.Default.Email, contentDescription = null)
                Spacer(Modifier.width(8.dp))
                Text("Εισαγωγή από Αρχείο (.xlsx, .pdf, .docx)")
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
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(addr.toString(), modifier = Modifier.weight(1f))
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
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = addresses.size >= 2
            ) {
                Text("Δημιουργία Δρομολογίου")
            }
            
            // Watch for calculation success to navigate
            LaunchedEffect(viewModel.routeLegs.collectAsState().value) {
                if (viewModel.routeLegs.value.isNotEmpty() && !viewModel.isCalculating.value) {
                    onNavigateToRoute()
                }
            }
        }
    }
}
