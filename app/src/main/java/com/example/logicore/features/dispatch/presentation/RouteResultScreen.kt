package com.example.logicore.features.dispatch.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RouteResultScreen(
    viewModel: ManualRoutingViewModel
) {
    val legs by viewModel.routeLegs.collectAsState()
    val isCalculating by viewModel.isCalculating.collectAsState()
    val drivers by viewModel.drivers.collectAsState()
    val error by viewModel.error.collectAsState()
    
    var selectedDriverId by remember { mutableStateOf<String?>(null) }
    var dropdownExpanded by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Βελτιστοποιημένο Δρομολόγιο") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            error?.let {
                Text(it, color = MaterialTheme.colorScheme.primary, style = MaterialTheme.typography.bodySmall)
                Spacer(Modifier.height(8.dp))
            }

            if (isCalculating) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            } else if (legs.isEmpty()) {
                Text("Δεν βρέθηκε έγκυρο δρομολόγιο.")
            } else {
                Text(
                    "Λεπτομέρειες Διαδρομής:",
                    style = MaterialTheme.typography.titleMedium
                )
                
                val totalDist = legs.sumOf { it.result.distanceMeters } / 1000.0
                val totalTime = legs.sumOf { it.result.durationSeconds } / 60.0
                
                Text(
                    text = String.format("Συνολική Απόσταση: %.2f km", totalDist),
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = String.format("Συνολικός Χρόνος: %.0f λεπτά", totalTime),
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(Modifier.height(16.dp))

                Text("Επιλογή Οδηγού:", style = MaterialTheme.typography.labelLarge)
                Box(modifier = Modifier.fillMaxWidth()) {
                    val selectedDriverName = drivers.find { it.id == selectedDriverId }?.let { "${it.firstName} ${it.lastName}" } ?: "Επιλέξτε Οδηγό"
                    OutlinedButton(
                        onClick = { dropdownExpanded = true },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(selectedDriverName)
                    }
                    DropdownMenu(
                        expanded = dropdownExpanded,
                        onDismissRequest = { dropdownExpanded = false }
                    ) {
                        drivers.forEach { driver ->
                            DropdownMenuItem(
                                text = { Text("${driver.firstName} ${driver.lastName}") },
                                onClick = {
                                    selectedDriverId = driver.id
                                    dropdownExpanded = false
                                }
                            )
                        }
                    }
                }

                Spacer(Modifier.height(8.dp))

                Button(
                    onClick = { selectedDriverId?.let { viewModel.dispatchRoute(it) } },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = selectedDriverId != null
                ) {
                    Text("Αποστολή στον Οδηγό")
                }

                Spacer(Modifier.height(16.dp))

                LazyColumn {
                    itemsIndexed(legs) { index, leg ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                        ) {
                            Column(Modifier.padding(12.dp)) {
                                Text(
                                    "Στάση ${index + 1} -> ${index + 2}",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.primary
                                )
                                Text("Από: ${leg.from}", style = MaterialTheme.typography.bodySmall)
                                Text("Προς: ${leg.to}", style = MaterialTheme.typography.bodySmall)
                                Spacer(Modifier.height(4.dp))
                                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                                    Text(
                                        String.format("%.2f km", leg.result.distanceMeters / 1000.0),
                                        style = MaterialTheme.typography.bodyMedium
                                    )
                                    Text(
                                        String.format("%.0f λεπτά", leg.result.durationSeconds / 60.0),
                                        style = MaterialTheme.typography.bodyMedium
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
