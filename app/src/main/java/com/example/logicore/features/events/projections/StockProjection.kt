package com.example.logicore.features.events.projections

import com.example.logicore.features.events.data.local.EventStoreEntity
import com.example.logicore.features.stock.events.StockConsumedEvent
import com.example.logicore.features.stock.events.StockReceivedEvent
import com.example.logicore.features.stock.projection.StockProjectionDao
import com.example.logicore.features.stock.projection.StockProjectionEntity
import com.google.gson.Gson

class StockProjection(
    private val dao: StockProjectionDao,
    private val gson: Gson
) : EventProjection {

    override fun supports(event: EventStoreEntity): Boolean {

        return event.eventType in listOf(
            "STOCK_RECEIVED",
            "STOCK_CONSUMED"
        )
    }

    override suspend fun handle(event: EventStoreEntity) {

        when (event.eventType) {

            "STOCK_RECEIVED" -> {

                val payload = gson.fromJson(
                    event.payload,
                    StockReceivedEvent::class.java
                )

                applyReceive(payload)
            }

            "STOCK_CONSUMED" -> {

                val payload = gson.fromJson(
                    event.payload,
                    StockConsumedEvent::class.java
                )

                applyConsume(payload)
            }
        }
    }

    private suspend fun applyReceive(event: StockReceivedEvent) {

        val current = dao.find(
            tenantId = event.tenantId,
            productId = event.productId,
            locationId = event.locationId
        )

        val newQty = (current?.quantity ?: 0.0) + event.quantity

        dao.upsert(
            StockProjectionEntity(
                tenantId = event.tenantId,
                productId = event.productId,
                locationId = event.locationId,
                quantity = newQty
            )
        )
    }

    private suspend fun applyConsume(event: StockConsumedEvent) {

        val current = dao.find(
            tenantId = event.tenantId,
            productId = event.productId,
            locationId = event.locationId
        )

        val newQty = (current?.quantity ?: 0.0) - event.quantity

        dao.upsert(
            StockProjectionEntity(
                tenantId = event.tenantId,
                productId = event.productId,
                locationId = event.locationId,
                quantity = newQty
            )
        )
    }
}