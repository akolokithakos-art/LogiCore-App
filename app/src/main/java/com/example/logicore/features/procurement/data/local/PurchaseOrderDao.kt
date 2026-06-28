package com.example.logicore.features.procurement.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PurchaseOrderDao {

    @Insert
    suspend fun insertOrder(
        order: PurchaseOrderEntity
    ): Long

    @Insert
    suspend fun insertLine(
        line: PurchaseOrderLineEntity
    )

    @Query("""
    SELECT *
    FROM purchase_order_lines
    WHERE purchaseOrderId = :orderId
      AND tenantId = :tenantId
""")
    suspend fun getLinesForOrder(
        tenantId: String,
        orderId: String
    ): List<PurchaseOrderLineEntity>

    @Query("""
        SELECT *
        FROM purchase_orders
        WHERE tenantId = :tenantId
        ORDER BY createdAt DESC
    """)
    suspend fun getOrders(
        tenantId: String
    ): List<PurchaseOrderEntity>
}