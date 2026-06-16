package com.example.logicore.features.warehouse.events.store

import com.google.gson.Gson
import com.example.logicore.features.warehouse.events.model.WarehouseEvent

class EventSerializer {

    private val gson = Gson()

    fun toJson(event: WarehouseEvent): String {
        return gson.toJson(event)
    }

    fun fromJson(type: String, json: String): WarehouseEvent {
        return when (type) {

            "StockChanged" ->
                gson.fromJson(json, WarehouseEvent.StockChanged::class.java)

            "ForecastGenerated" ->
                gson.fromJson(json, WarehouseEvent.ForecastGenerated::class.java)

            "ReorderTriggered" ->
                gson.fromJson(json, WarehouseEvent.ReorderTriggered::class.java)

            "PurchaseOrderCreated" ->
                gson.fromJson(json, WarehouseEvent.PurchaseOrderCreated::class.java)

            "PurchaseOrderDispatched" ->
                gson.fromJson(json, WarehouseEvent.PurchaseOrderDispatched::class.java)

            "PurchaseOrderFailed" ->
                gson.fromJson(json, WarehouseEvent.PurchaseOrderFailed::class.java)

            else -> throw IllegalArgumentException("Unknown event type: $type")
        }
    }
}