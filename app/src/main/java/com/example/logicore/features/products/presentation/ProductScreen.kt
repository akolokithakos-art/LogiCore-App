package com.example.logicore.features.products.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ProductScreen(
    viewModel: ProductViewModel
) {
    val products by viewModel.products.collectAsState()

    var name by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "PRODUCTS",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(10.dp))

        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Product Name") }
        )

        TextField(
            value = price,
            onValueChange = { price = it },
            label = { Text("Price") }
        )

        Button(
            onClick = {

                viewModel.addProduct(
                    code = "P${System.currentTimeMillis()}",
                    barcode = "",
                    name = name,
                    price = price.toDoubleOrNull() ?: 0.0,
                    stock = 0.0
                )

                name = ""
                price = ""
            }
        ) {
            Text("Add Product")
        }

        Spacer(modifier = Modifier.height(20.dp))

        LazyColumn {
            items(products) { product ->

                Text(
                    text = "${product.name} - ${product.price}"
                )

                HorizontalDivider()
            }
        }
    }
}