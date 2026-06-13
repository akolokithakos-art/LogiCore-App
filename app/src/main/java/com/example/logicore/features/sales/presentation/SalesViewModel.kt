package com.example.logicore.features.sales.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.logicore.features.customerbalance.domain.CreditControlEngine
import com.example.logicore.features.sales.data.local.SaleEntity
import com.example.logicore.features.sales.data.local.SaleLineEntity
import com.example.logicore.features.sales.data.local.SalesDao
import com.example.logicore.features.stock.domain.StockEngine
import com.example.logicore.features.tenant.core.TenantContext
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SalesViewModel(
    private val dao: SalesDao,
    private val stockEngine: StockEngine,
    private val creditControlEngine: CreditControlEngine,
    private val tenantContext: TenantContext
) : ViewModel() {

    val sales = tenantContext.currentTenant
        .flatMapLatest { tenantId ->
            if (tenantId == null) {
                flowOf(emptyList())
            } else {
                dao.getSales(tenantId)
            }
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            emptyList()
        )

    private var currentSaleId: Int? = null
    private var selectedCustomerId: Int? = null

    fun selectCustomer(customerId: Int) {
        selectedCustomerId = customerId
    }

    fun startSale() {
        viewModelScope.launch {

            val customerId = selectedCustomerId ?: return@launch

            val tenantId = tenantContext.getTenant()
                ?: throw IllegalStateException("No tenant selected")

            val id = dao.createSale(
                SaleEntity(
                    tenantId = tenantId,
                    customerId = customerId,
                    status = "DRAFT"
                )
            )

            currentSaleId = id.toInt()
        }
    }

    fun addProduct(
        productId: Int,
        qty: Double,
        price: Double
    ) {

        val saleId = currentSaleId ?: return

        viewModelScope.launch {

            val customerId = selectedCustomerId ?: return@launch

            val tenantId = tenantContext.getTenant()
                ?: throw IllegalStateException("No tenant selected")

            val saleAmount = qty * price

            val creditAllowed = creditControlEngine.canSell(
                customerId = customerId,
                saleAmount = saleAmount
            )

            if (!creditAllowed) return@launch

            val stockAllowed = stockEngine.canSell(
                productId = productId,
                vehicleId = 1,
                qty = qty
            )

            if (!stockAllowed) return@launch

            dao.addLine(
                SaleLineEntity(
                    tenantId = tenantId,
                    saleId = saleId,
                    productId = productId,
                    quantity = qty,
                    price = price
                )
            )

            val stockSuccess = stockEngine.registerSale(
                productId = productId,
                vehicleId = 1,
                qty = qty
            )

            if (stockSuccess) {
                creditControlEngine.registerSale(
                    customerId = customerId,
                    saleAmount = saleAmount
                )
            }
        }
    }
}