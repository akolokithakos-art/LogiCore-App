package com.example.logicore.features.customerbalance.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "customer_balances")
data class CustomerBalanceEntity(

    @PrimaryKey
    val customerId: Int,

    val currentBalance: Double = 0.0,

    val creditLimit: Double = 0.0,

    val creditDays: Int = 0,

    val blocked: Boolean = false
)