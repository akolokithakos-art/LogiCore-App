package com.example.logicore.features.products.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.logicore.features.products.data.ProductRepository
import com.example.logicore.features.products.data.local.ProductEntity
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ProductViewModel(
    private val repository: ProductRepository
) : ViewModel() {

    val products = repository.getProducts()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    fun addProduct(product: ProductEntity) {
        viewModelScope.launch {
            repository.addProduct(product)
        }
    }
}