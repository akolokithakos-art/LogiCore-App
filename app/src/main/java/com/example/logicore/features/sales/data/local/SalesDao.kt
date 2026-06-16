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
    SELECT *
    FROM sale_lines
    WHERE tenantId = :tenantId
""")
    suspend fun getAllLines(
        tenantId: String
    ): List<SaleLineEntity>

    @Query("""
    SELECT *
    FROM sale_lines
    WHERE tenantId = :tenantId
      AND saleId IN (
          SELECT id
          FROM sales
          WHERE tenantId = :tenantId
            AND createdAt >= :fromTimestamp
      )
""")
    suspend fun getLinesSince(
        tenantId: String,
        fromTimestamp: Long
    ): List<SaleLineEntity>

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