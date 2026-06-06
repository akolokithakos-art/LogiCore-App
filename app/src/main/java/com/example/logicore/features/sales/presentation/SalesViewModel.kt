package com.example.logicore.features.sales.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.logicore.features.sales.data.local.SaleEntity
import com.example.logicore.features.sales.data.local.SalesDao
import com.example.logicore.features.sales.data.local.SaleLineEntity
import com.example.logicore.features.stock.domain.StockEngine
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SalesViewModel(
    private val dao: SalesDao,
    private val stockEngine: StockEngine
) : ViewModel() {

    val sales = dao.getSales()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    private var currentSaleId: Int? = null
    private var selectedCustomerId: Int? = null

    fun selectCustomer(customerId: Int) {
        selectedCustomerId = customerId
    }

    fun startSale() {
        viewModelScope.launch {

            val customerId = selectedCustomerId ?: return@launch

            val id = dao.createSale(
                SaleEntity(
                    customerId = customerId,
                    status = "DRAFT"
                )
            )

            currentSaleId = id.toInt()
        }
    }

    fun addProduct(productId: Int, qty: Double, price: Double) {
        val saleId = currentSaleId ?: return

        viewModelScope.launch {

            dao.addLine(
                SaleLineEntity(
                    saleId = saleId,
                    productId = productId,
                    quantity = qty,
                    price = price
                )
            )

            // 🔥 STOCK INTEGRATION
            stockEngine.registerSale(
                productId = productId,
                vehicleId = 1,
                qty = qty
            )
        }
    }
}