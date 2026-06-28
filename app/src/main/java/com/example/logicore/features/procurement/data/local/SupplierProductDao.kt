package com.example.logicore.features.procurement.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SupplierProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(
        entity: SupplierProductEntity
    )

    @Query("""
    SELECT *
    FROM supplier_products
    WHERE tenantId = :tenantId
      AND productId = :productId
      AND active = 1
""")
    suspend fun getSuppliersForProduct(
        tenantId: String,
        productId: String
    ): List<SupplierProductEntity>

    @Query("""
    SELECT *
    FROM supplier_products
    WHERE tenantId = :tenantId
      AND supplierId = :supplierId
      AND active = 1
""")
    suspend fun getProductsForSupplier(
        tenantId: String,
        supplierId: String
    ): List<SupplierProductEntity>

    @Query("""
    SELECT *
    FROM supplier_products
    WHERE tenantId = :tenantId
      AND productId = :productId
      AND preferredSupplier = 1
      AND active = 1
    LIMIT 1
""")
    suspend fun getPreferredSupplier(
        tenantId: String,
        productId: String
    ): SupplierProductEntity?

    @Query("""
    DELETE FROM supplier_products
    WHERE tenantId = :tenantId
""")
    suspend fun clearTenant(
        tenantId: String
    )

}
