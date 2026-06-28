package com.example.logicore.features.address.domain.model

import com.example.logicore.features.address.domain.AddressGeocoder

class TemporaryGeocoder : AddressGeocoder {

    override suspend fun geocode(
        street: String,
        number: String,
        city: String,
        postalCode: String
    ): GeoPoint? {

        return when {

            street.contains("ΑΧΑΡΝΩΝ", true) ->
                GeoPoint(
                    latitude = 38.0380,
                    longitude = 23.7390
                )

            street.contains("ΠΑΠΑΝΔΡΕΟΥ", true) ->
                GeoPoint(
                    latitude = 38.0640,
                    longitude = 23.7630
                )

            else -> null
        }
    }
}
