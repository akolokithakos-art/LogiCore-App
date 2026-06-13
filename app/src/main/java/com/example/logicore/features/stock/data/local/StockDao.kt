package com.example.logicore.features.stock.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface StockDao {

    @Insert
    suspend fun insertMovement(movement: StockMovementEntity)

    @Insert
    suspend fun insertLocation(location: StockLocationEntity)

    @Query("""
        SELECT * 
        FROM stock_movements
        WHERE tenantId = :tenantId
        ORDER BY createdAt DESC
    """)
    fun getMovements(tenantId: String): Flow<List<StockMovementEntity>>

    @Query("""
        SELECT * 
        FROM stock_movements
        WHERE tenantId = :tenantId
    """)
    suspend fun getMovementsList(tenantId: String): List<StockMovementEntity>

    @Query("""
        SELECT COALESCE(SUM(
            CASE 
                WHEN toLocationId = :locationId THEN quantity
                WHEN fromLocationId = :locationId THEN -quantity
                ELSE 0
            END
        ), 0)
        FROM stock_movements
        WHERE tenantId = :tenantId
          AND productId = :productId
    """)
    suspend fun getStockBalance(
        tenantId: String,
        productId: Int,
        locationId: Int
    ): Double
}