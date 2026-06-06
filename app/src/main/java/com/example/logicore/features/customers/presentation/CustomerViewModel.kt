package com.example.logicore.features.customers.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.logicore.features.customers.data.local.CustomerDao
import com.example.logicore.features.customers.data.local.CustomerEntity
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CustomerViewModel(
    private val dao: CustomerDao
) : ViewModel() {

    val customers = dao.getAll()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    fun addCustomer(name: String, phone: String) {
        viewModelScope.launch {
            dao.insert(
                CustomerEntity(
                    name = name,
                    phone = phone,
                    code = "CUST-${System.currentTimeMillis()}"
                )
            )
        }
    }
}