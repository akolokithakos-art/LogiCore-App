package com.example.logicore.features.products.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Query("""
        SELECT * FROM products
        WHERE tenantId = :tenantId
        ORDER BY name ASC
    """)
    fun getAllProducts(tenantId: String): Flow<List<ProductEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: ProductEntity)

    @Query("DELETE FROM products WHERE tenantId = :tenantId")
    suspend fun clearAll(tenantId: String)
}