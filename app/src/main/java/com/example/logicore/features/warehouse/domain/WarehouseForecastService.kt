package com.example.logicore.features.warehouse.domain

import com.example.logicore.features.sales.data.local.SalesDao
import com.example.logicore.features.tenant.core.TenantContext
import com.example.logicore.features.warehouse.domain.model.ProductForecast

class WarehouseForecastService(
    private val salesDao: SalesDao,
    private val tenantContext: TenantContext,
    private val forecastEngine: ForecastEngine
) {

    private fun tenant(): String =
        tenantContext.getTenant()
            ?: throw IllegalStateException("No tenant selected")

    suspend fun generateForecast():
            List<ProductForecast> {

        val lines =
            salesDao.getAllLines(
                tenant()
            )

        return forecastEngine.forecast(lines)
    }
}