package com.example.logicore.features.customerbalance.data

import com.example.logicore.features.customerbalance.data.local.CustomerBalanceDao
import com.example.logicore.features.customerbalance.data.local.CustomerBalanceEntity
import kotlinx.coroutines.flow.Flow

class CustomerBalanceRepository(
    private val dao: CustomerBalanceDao
) {

    fun getAllBalances(): Flow<List<CustomerBalanceEntity>> {
        return dao.getAllBalances()
    }

    suspend fun getBalance(
        customerId: Int
    ): CustomerBalanceEntity? {
        return dao.getBalance(customerId)
    }

    suspend fun save(
        balance: CustomerBalanceEntity
    ) {
        dao.save(balance)
    }

    suspend fun updateBalance(
        customerId: Int,
        newBalance: Double
    ) {
        dao.updateBalance(
            customerId = customerId,
            newBalance = newBalance
        )
    }

    suspend fun setBlocked(
        customerId: Int,
        blocked: Boolean
    ) {
        dao.setBlocked(
            customerId = customerId,
            blocked = blocked
        )
    }
}