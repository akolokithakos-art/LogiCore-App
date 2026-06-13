package com.example.logicore.features.dashboard.security

import androidx.compose.runtime.*
import com.example.logicore.features.auth.AuthSessionManager
import com.example.logicore.features.auth.security.PermissionEngine
import com.example.logicore.features.dashboard.ui.ControlCenterScreen
import com.example.logicore.features.dashboard.presentation.ControlCenterViewModel
import androidx.compose.material3.Text


@Composable
fun SecuredControlCenter(
    vm: ControlCenterViewModel,
    auth: AuthSessionManager,
    permissions: PermissionEngine
) {

    val user by auth.user.collectAsState()

    if (user == null) {
        Text("NOT AUTHORIZED")
        return
    }

    if (!permissions.canAccessDashboard(user!!.role)) {
        Text("ACCESS DENIED")
        return
    }

    ControlCenterScreen(vm)
}