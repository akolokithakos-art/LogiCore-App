package com.example.logicore.features.stock.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.logicore.features.customers.data.local.CustomerEntity
import com.example.logicore.features.products.data.local.ProductEntity
import com.example.logicore.features.sales.data.local.SaleEntity
import com.example.logicore.features.sales.data.local.SaleLineEntity

@Database(
    entities = [
        ProductEntity::class,
        SaleEntity::class,
        SaleLineEntity::class,
        CustomerEntity::class,
        StockMovementEntity::class,
        StockLocationEntity::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun stockDao(): StockDao
}