package com.example.logicore.features.stock.projection

import com.example.logicore.features.events.data.local.EventStoreEntity
import com.example.logicore.features.events.projections.EventProcessedDao
import com.example.logicore.features.events.projections.ProcessedEventEntity
import com.example.logicore.features.stock.events.StockConsumedEvent
import com.example.logicore.features.stock.events.StockReceivedEvent
import com.google.gson.Gson

class StockProjection(
    private val stockDao: StockProjectionDao,
    private val processedDao: EventProcessedDao,
    private val gson: Gson
) {

    suspend fun handle(event: EventStoreEntity) {

        // 1. IDEMPOTENCY CHECK
        if (processedDao.exists(event.aggregateId ?: event.id.toString()) > 0) {
            return
        }

        when (event.eventType) {

            "STOCK_RECEIVED" -> {

                val e = gson.fromJson(
                    event.payload,
                    StockReceivedEvent::class.java
                )

                applyReceive(e)
            }

            "STOCK_CONSUMED" -> {

                val e = gson.fromJson(
                    event.payload,
                    StockConsumedEvent::class.java
                )

                applyConsume(e)
            }
        }

        // 2. MARK PROCESSED
        processedDao.markProcessed(
            ProcessedEventEntity(
                eventId = event.id.toString()
            )
        )
    }

    private suspend fun applyReceive(e: StockReceivedEvent) {

        val current = stockDao.find(
            e.tenantId,
            e.productId,
            e.locationId
        )

        val newQty = (current?.quantity ?: 0.0) + e.quantity

        stockDao.upsert(
            StockProjectionEntity(
                tenantId = e.tenantId,
                productId = e.productId,
                locationId = e.locationId,
                quantity = newQty
            )
        )
    }

    private suspend fun applyConsume(e: StockConsumedEvent) {

        val current = stockDao.find(
            e.tenantId,
            e.productId,
            e.locationId
        )

        val newQty = (current?.quantity ?: 0.0) - e.quantity

        stockDao.upsert(
            StockProjectionEntity(
                tenantId = e.tenantId,
                productId = e.productId,
                locationId = e.locationId,
                quantity = newQty
            )
        )
    }
}