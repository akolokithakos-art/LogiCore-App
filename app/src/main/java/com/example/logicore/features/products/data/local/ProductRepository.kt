package com.example.logicore.features.products.data

import com.example.logicore.features.products.data.local.ProductDao
import com.example.logicore.features.products.data.local.ProductEntity
import kotlinx.coroutines.flow.Flow

class ProductRepository(
    private val dao: ProductDao
) {
    fun getProducts(): Flow<List<ProductEntity>> = dao.getAllProducts()

    suspend fun addProduct(product: ProductEntity) {
        dao.insertProduct(product)
    }
}