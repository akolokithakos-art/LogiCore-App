package com.example.logicore.features.address.domain

import com.example.logicore.features.address.data.AddressRepository
import com.example.logicore.features.address.data.local.AddressEntity

class AddressService(
    private val repo: AddressRepository
) {

    suspend fun createAddress(
        referenceType: String,
        referenceId: Int,
        street: String,
        number: String,
        city: String,
        postalCode: String? = null
    ) {

        repo.save(
            AddressEntity(
                tenantId = "",
                referenceType = referenceType,
                referenceId = referenceId,
                street = street,
                number = number,
                city = city,
                postalCode = postalCode,
                latitude = null,
                longitude = null
            )
        )
    }

    suspend fun getAllAddresses(): List<AddressEntity> {
        return repo.getAll()
    }
}