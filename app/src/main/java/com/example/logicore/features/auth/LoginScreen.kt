package com.example.logicore.features.auth

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Scaffold {
        Column {
            Text("LOGICORE LOGIN")

            TextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Username") }
            )

            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") }
            )

            Button(onClick = {
                onLoginSuccess()
            }) {
                Text("Login")
            }
        }
    }
}