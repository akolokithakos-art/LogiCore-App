package com.example.logicore.features.address.data

import com.example.logicore.features.address.data.local.AddressDao
import com.example.logicore.features.address.data.local.AddressEntity
import com.example.logicore.features.tenant.core.TenantContext

class AddressRepository(
    private val dao: AddressDao,
    private val tenantContext: TenantContext
) {

    private fun tenant(): String =
        tenantContext.getTenant()
            ?: throw IllegalStateException("No tenant selected")

    suspend fun save(
        address: AddressEntity
    ) {
        dao.insert(address)
    }

    suspend fun getAll(): List<AddressEntity> {
        return dao.getAll(
            tenantId = tenant()
        )
    }

    suspend fun getAddress(
        referenceType: String,
        referenceId: Int
    ): AddressEntity? {

        return dao.getAddress(
            tenantId = tenant(),
            referenceType = referenceType,
            referenceId = referenceId
        )
    }
}