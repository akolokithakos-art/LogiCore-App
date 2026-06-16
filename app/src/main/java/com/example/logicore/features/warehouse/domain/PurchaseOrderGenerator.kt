package com.example.logicore.features.warehouse.domain

import com.example.logicore.features.warehouse.domain.model.ProcurementSuggestion
import com.example.logicore.features.warehouse.domain.model.PurchaseOrderDraft
import com.example.logicore.features.warehouse.domain.model.PurchaseOrderDraftLine

class PurchaseOrderGenerator {

    fun generate(
        suggestions: List<ProcurementSuggestion>
    ): List<PurchaseOrderDraft> {

        return suggestions
            .groupBy { it.supplierId }
            .map { (supplierId, items) ->

                PurchaseOrderDraft(
                    supplierId = supplierId,
                    lines = items.map {

                        PurchaseOrderDraftLine(
                            productId = it.productId,
                            quantity = it.suggestedQty
                        )
                    }
                )
            }
    }
}