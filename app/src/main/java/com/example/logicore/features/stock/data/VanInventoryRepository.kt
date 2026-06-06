package com.example.logicore.features.stock.data

import com.example.logicore.features.stock.data.local.StockDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class VanInventoryRepository(
    private val dao: StockDao
) {

    fun getVanStock(vanId: Int): Flow<Map<Int, Double>> {

        return dao.getMovements().map { movements ->

            val result = mutableMapOf<Int, Double>()

            movements.forEach { m ->

                if (m.productId == null) return@forEach

                if (m.fromLocationId == vanId) {
                    result[m.productId] = (result[m.productId] ?: 0.0) - m.quantity
                }

                if (m.toLocationId == vanId) {
                    result[m.productId] = (result[m.productId] ?: 0.0) + m.quantity
                }
            }

            result
        }
    }
}