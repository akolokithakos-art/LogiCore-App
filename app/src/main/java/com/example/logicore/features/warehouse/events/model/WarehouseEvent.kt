package com.example.logicore.features.warehouse.events.model

sealed class WarehouseEvent {

    data class StockReceived(
        val orderId: Long,
        val productId: Int,
        val quantity: Double,
        val warehouseId: Int
    ) : WarehouseEvent()

    data class StockConsumed(
        val productId: Int,
        val quantity: Double
    ) : WarehouseEvent()

    data class StockChanged(
        val productId: Int,
        val locationId: Int,
        val delta: Double
    ) : WarehouseEvent()

    data class StockAnomalyDetected(
        val productId: Int,
        val severity: Double,
        val expected: Double,
        val actual: Double
    ) : WarehouseEvent()

    data class ForecastGenerated(
        val productId: Int,
        val forecast30Days: Double
    ) : WarehouseEvent()

    data class ReorderTriggered(
        val productId: Int,
        val suggestedQty: Double
    ) : WarehouseEvent()

    data class PurchaseOrderCreated(
        val orderId: Int,
        val supplierId: Int
    ) : WarehouseEvent()

    data class PurchaseOrderDispatched(
        val orderId: Int,
        val externalRef: String?
    ) : WarehouseEvent()

    data class PurchaseOrderFailed(
        val orderId: Int,
        val reason: String
    ) : WarehouseEvent()
}