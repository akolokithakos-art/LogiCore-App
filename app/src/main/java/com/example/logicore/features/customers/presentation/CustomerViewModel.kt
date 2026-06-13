package com.example.logicore.features.customers.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.logicore.features.customers.data.local.CustomerDao
import com.example.logicore.features.customers.data.local.CustomerEntity
import com.example.logicore.features.tenant.core.TenantContext
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CustomerViewModel(
    private val dao: CustomerDao,
    private val tenantContext: TenantContext
) : ViewModel() {

    val customers = tenantContext.currentTenant
        .flatMapLatest { tenantId ->
            if (tenantId == null) {
                flowOf(emptyList())
            } else {
                dao.getAll(tenantId)
            }
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            emptyList()
        )

    fun addCustomer(name: String, phone: String) {
        viewModelScope.launch {

            val tenantId = tenantContext.getTenant()
                ?: return@launch

            dao.insert(
                CustomerEntity(
                    tenantId = tenantId,
                    name = name,
                    phone = phone,
                    code = "CUST-${System.currentTimeMillis()}"
                )
            )
        }
    }
}