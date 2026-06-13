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

    @Query("""
        SELECT * FROM sales
        WHERE tenantId = :tenantId
        ORDER BY createdAt DESC
    """)
    fun getSales(tenantId: String): Flow<List<SaleEntity>>

    @Query("""
        SELECT * FROM sale_lines
        WHERE saleId = :saleId AND tenantId = :tenantId
    """)
    fun getLines(
        saleId: Int,
        tenantId: String
    ): Flow<List<SaleLineEntity>>
}