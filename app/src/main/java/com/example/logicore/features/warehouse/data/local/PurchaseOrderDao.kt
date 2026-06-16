package com.example.logicore.features.warehouse.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PurchaseOrderDao {

    @Insert
    suspend fun insertOrder(order: PurchaseOrderEntity): Long

    @Insert
    suspend fun insertLine(line: PurchaseOrderLineEntity)

    @Query("""
        SELECT * 
        FROM purchase_orders
        WHERE tenantId = :tenantId
        ORDER BY createdAt DESC
    """)
    fun getOrders(tenantId: String): Flow<List<PurchaseOrderEntity>>

    @Query("""
    UPDATE purchase_orders
    SET status = :status
    WHERE id = :orderId
""")
    suspend fun updateStatus(
        orderId: Int,
        status: String
    )

    @Query("""
    UPDATE purchase_orders
    SET status = :status
    WHERE id = :orderId
""")
    suspend fun updateStatus(
        orderId: Int,
        status: String
    )

    @Query("""
        SELECT * 
        FROM purchase_order_lines
        WHERE purchaseOrderId = :orderId
    """)
    fun getLines(orderId: Int): Flow<List<PurchaseOrderLineEntity>>
}