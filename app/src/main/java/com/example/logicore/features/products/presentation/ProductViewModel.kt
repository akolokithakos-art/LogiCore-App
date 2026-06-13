package com.example.logicore.features.products.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.logicore.features.products.data.ProductRepository
import com.example.logicore.features.products.data.local.ProductEntity
import com.example.logicore.features.tenant.core.TenantContext
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ProductViewModel(
    private val repository: ProductRepository,
    private val tenantContext: TenantContext
) : ViewModel() {

    val products = tenantContext.currentTenant
        .flatMapLatest { tenantId ->
            if (tenantId == null) {
                flowOf(emptyList())
            } else {
                repository.getProducts(tenantId)
            }
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            emptyList()
        )

    fun addProduct(
        code: String,
        barcode: String,
        name: String,
        price: Double,
        stock: Double
    ) {
        viewModelScope.launch {

            val tenantId = tenantContext.getTenant()
                ?: return@launch

            repository.addProduct(
                ProductEntity(
                    tenantId = tenantId,
                    code = code,
                    barcode = barcode,
                    name = name,
                    price = price,
                    stock = stock
                )
            )
        }
    }
}