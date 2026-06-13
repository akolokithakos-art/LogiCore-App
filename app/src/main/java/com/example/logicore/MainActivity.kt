package com.example.logicore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.logicore.navigation.AppNavGraph
import com.example.logicore.ui.theme.LogicoreTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            LogicoreTheme {
                AppNavGraph()
            }
        }
    }
}