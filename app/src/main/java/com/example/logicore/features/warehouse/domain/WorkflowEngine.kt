package com.example.logicore.features.warehouse.domain

import com.example.logicore.features.warehouse.domain.model.*
import kotlin.math.max

class WorkflowEngine {

    private val AUTO_APPROVAL_LIMIT = 500.0
    private val CRITICAL_LIMIT = 1500.0

    fun evaluate(
        orderId: Int,
        totalValue: Double,
        supplierPriority: Int
    ): WorkflowDecision {

        return when {

            totalValue <= AUTO_APPROVAL_LIMIT -> {
                WorkflowDecision(
                    orderId,
                    PurchaseOrderStatus.AUTO_APPROVED,
                    "Under auto approval limit"
                )
            }

            totalValue <= CRITICAL_LIMIT && supplierPriority >= 7 -> {
                WorkflowDecision(
                    orderId,
                    PurchaseOrderStatus.APPROVED,
                    "Medium value + trusted supplier"
                )
            }

            totalValue > CRITICAL_LIMIT -> {
                WorkflowDecision(
                    orderId,
                    PurchaseOrderStatus.HOLD,
                    "High value requires manual review"
                )
            }

            else -> {
                WorkflowDecision(
                    orderId,
                    PurchaseOrderStatus.REJECTED,
                    "Does not meet procurement rules"
                )
            }
        }
    }
}