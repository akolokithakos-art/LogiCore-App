package com.example.logicore.features.stock.projection

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface StockProjectionDao {

    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    suspend fun upsert(
        projection: StockProjectionEntity
    )

    @Query("""
        SELECT *
        FROM stock_projection
        WHERE tenantId = :tenantId
          AND productId = :productId
          AND locationId = :locationId
        LIMIT 1
    """)
    suspend fun find(
        tenantId: String,
        productId: Int,
        locationId: Int
    ): StockProjectionEntity?
}