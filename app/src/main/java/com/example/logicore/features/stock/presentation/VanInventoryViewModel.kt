package com.example.logicore.features.stock.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.logicore.features.stock.data.VanInventoryRepository
import com.example.logicore.features.stock.presentation.model.VanStockItem
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.map

class VanInventoryViewModel(
    private val repo: VanInventoryRepository
) : ViewModel() {

    private val vanId = 1

    val stock = repo.getVanStock(vanId)
        .map { map ->
            map.map { entry ->
                VanStockItem(
                    productId = entry.key,
                    productName = "Product ${entry.key}", // προσωρινό
                    quantity = entry.value
                )
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
}