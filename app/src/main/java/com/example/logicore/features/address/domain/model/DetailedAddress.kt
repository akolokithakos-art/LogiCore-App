package com.example.logicore.features.address.domain.model

data class DetailedAddress(
    val street: String,
    val number: String = "",
    val area: String,
    val postalCode: String = ""
) {
    override fun toString(): String {
        return "$street $number, $area $postalCode".trim()
    }
}
