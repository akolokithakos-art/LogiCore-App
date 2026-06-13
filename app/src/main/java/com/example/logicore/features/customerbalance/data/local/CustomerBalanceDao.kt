package com.example.logicore.features.customerbalance.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CustomerBalanceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(balance: CustomerBalanceEntity)

    @Query("""
        SELECT *
        FROM customer_balances
        WHERE customerId = :customerId
        LIMIT 1
    """)
    suspend fun getBalance(
        customerId: Int
    ): CustomerBalanceEntity?

    @Query("""
        SELECT *
        FROM customer_balances
        ORDER BY customerId
    """)
    fun getAllBalances(): Flow<List<CustomerBalanceEntity>>

    @Query("""
        UPDATE customer_balances
        SET currentBalance = :newBalance
        WHERE customerId = :customerId
    """)
    suspend fun updateBalance(
        customerId: Int,
        newBalance: Double
    )

    @Query("""
        UPDATE customer_balances
        SET blocked = :blocked
        WHERE customerId = :customerId
    """)
    suspend fun setBlocked(
        customerId: Int,
        blocked: Boolean
    )
}