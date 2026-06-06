package com.example.logicore.features.customers.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CustomerDao {

    @Insert
    suspend fun insert(customer: CustomerEntity)

    @Query("SELECT * FROM customers ORDER BY name ASC")
    fun getAll(): Flow<List<CustomerEntity>>

    @Query("SELECT * FROM customers WHERE id = :id")
    suspend fun getById(id: Int): CustomerEntity?
}