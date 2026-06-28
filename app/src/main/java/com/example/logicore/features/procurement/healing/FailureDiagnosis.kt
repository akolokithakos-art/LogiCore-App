package com.example.logicore.features.procurement.healing

data class FailureDiagnosis(
    val type: FailureType,
    val confidence: Double,
    val reason: String
)

enum class FailureType {
    TRANSIENT,
    SUPPLIER_UNAVAILABLE,
    DATA_CONFLICT,
    UNKNOWN
}
