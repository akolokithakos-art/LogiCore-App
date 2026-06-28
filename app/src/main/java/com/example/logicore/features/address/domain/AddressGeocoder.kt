package com.example.logicore.features.address.domain

import com.example.logicore.features.address.domain.model.GeoPoint

interface AddressGeocoder {

    suspend fun geocode(
        street: String,
        number: String,
        city: String
    ): GeoPoint?
}
