package com.example.logicore.features.integration.pda

import com.example.logicore.features.integration.local.DispatchOperation

class PdaCommandEngine {

    fun generateCommand(op: DispatchOperation): String {

        return when (op.type) {

            "LOAD" ->
                "PRINT LOAD SHEET: ${op.payload}"

            "ROUTE_CHANGE" ->
                "UPDATE ROUTE: ${op.payload}"

            "STOCK_UPDATE" ->
                "SYNC STOCK: ${op.payload}"

            "PRINT_JOB" ->
                "PRINT DOCUMENT: ${op.payload}"

            else ->
                "UNKNOWN OPERATION"
        }
    }
}