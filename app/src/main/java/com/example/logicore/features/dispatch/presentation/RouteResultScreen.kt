package com.example.logicore.features.dispatch.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RouteResultScreen(
    viewModel: ManualRoutingViewModel
) {
    val route by viewModel.generatedRoute.collectAsState()

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
            Text(
                "Το AI δημιούργησε την καλύτερη διαδρομή:",
                style = MaterialTheme.typography.titleMedium
            )
            
            Spacer(Modifier.height(16.dp))

            LazyColumn {
                itemsIndexed(route) { index, addr ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                    ) {
                        Column(Modifier.padding(12.dp)) {
                            Text(
                                "Στάση ${index + 1}",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.primary
                            )
                            Text(addr, style = MaterialTheme.typography.bodyLarge)
                        }
                    }
                }
            }
        }
    }
}
