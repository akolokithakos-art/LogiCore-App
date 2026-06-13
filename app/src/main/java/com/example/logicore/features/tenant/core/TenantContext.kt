package com.example.logicore.features.tenant.core

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class TenantContext {

    private val _currentTenant =
        MutableStateFlow<String?>(null)

    val currentTenant: StateFlow<String?> = _currentTenant

    fun setTenant(tenantId: String) {
        _currentTenant.value = tenantId
    }

    fun getTenant(): String? = _currentTenant.value
}