package com.example.logicore.features.sales.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface SalesDao {

    @Insert
    suspend fun createSale(sale: SaleEntity): Long

    @Insert
    suspend fun addLine(line: SaleLineEntity)

    @Query("SELECT * FROM sales ORDER BY createdAt DESC")
    fun getSales(): Flow<List<SaleEntity>>

    @Query("SELECT * FROM sale_lines WHERE saleId = :saleId")
    fun getLines(saleId: Int): Flow<List<SaleLineEntity>>
}