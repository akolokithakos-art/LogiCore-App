package com.example.logicore.features.customerbalance.domain

import com.example.logicore.features.customerbalance.data.CustomerBalanceRepository

class CreditControlEngine(
    private val repository: CustomerBalanceRepository
) {

    suspend fun canSell(
        customerId: Int,
        saleAmount: Double
    ): Boolean {

        val balance = repository.getBalance(customerId)
            ?: return true

        if (balance.blocked) {
            return false
        }

        val futureBalance =
            balance.currentBalance + saleAmount

        return futureBalance <= balance.creditLimit
    }

    suspend fun registerSale(
        customerId: Int,
        saleAmount: Double
    ) {

        val balance = repository.getBalance(customerId)
            ?: return

        repository.updateBalance(
            customerId = customerId,
            newBalance = balance.currentBalance + saleAmount
        )
    }

    suspend fun registerPayment(
        customerId: Int,
        amount: Double
    ) {

        val balance = repository.getBalance(customerId)
            ?: return

        repository.updateBalance(
            customerId = customerId,
            newBalance = balance.currentBalance - amount
        )
    }
}