package com.example.logicore.features.core.database

import androidx.room.Database
import androidx.room.RoomDatabase

// AUTH
import com.example.logicore.features.auth.data.local.AppUserDao
import com.example.logicore.features.auth.data.local.AppUserEntity
import com.example.logicore.features.auth.data.local.RoleDao
import com.example.logicore.features.auth.data.local.RoleEntity

// TENANT
import com.example.logicore.features.tenant.data.local.TenantDao
import com.example.logicore.features.tenant.data.local.TenantEntity

// FLEET
import com.example.logicore.features.fleet.data.local.DriverDao
import com.example.logicore.features.fleet.data.local.VehicleDao
import com.example.logicore.features.fleet.data.local.DriverVehicleAssignmentDao

import com.example.logicore.features.fleet.data.local.DriverEntity
import com.example.logicore.features.fleet.data.local.VehicleEntity
import com.example.logicore.features.fleet.data.local.DriverVehicleAssignmentEntity

// EVENTS CORE
import com.example.logicore.features.events.data.local.EventStoreDao
import com.example.logicore.features.events.data.local.EventStoreEntity

import com.example.logicore.features.events.checkpoint.CheckpointDao
import com.example.logicore.features.events.checkpoint.CheckpointEntity

import com.example.logicore.features.events.resilience.retry.RetryQueueDao
import com.example.logicore.features.events.resilience.retry.RetryQueueEntity

import com.example.logicore.features.events.resilience.DeadLetterDao
import com.example.logicore.features.events.resilience.DeadLetterEntity

// PRODUCTS / SUPPLIERS
import com.example.logicore.features.products.data.local.ProductEntity
import com.example.logicore.features.suppliers.data.local.SupplierEntity
import com.example.logicore.features.suppliers.data.local.SupplierDao

import com.example.logicore.features.procurement.data.local.SupplierProductEntity
import com.example.logicore.features.procurement.data.local.SupplierProductDao

// PROCUREMENT
import com.example.logicore.features.procurement.data.local.PurchaseOrderDao
import com.example.logicore.features.procurement.data.local.PurchaseOrderEntity
import com.example.logicore.features.procurement.data.local.PurchaseOrderLineEntity

// STOCK
import com.example.logicore.features.stock.data.local.StockDao
import com.example.logicore.features.stock.data.local.StockLocationEntity
import com.example.logicore.features.stock.data.local.StockMovementEntity

import com.example.logicore.features.stock.projection.StockProjectionDao
import com.example.logicore.features.stock.projection.StockProjectionEntity

// SALES
import com.example.logicore.features.sales.data.local.SalesDao
import com.example.logicore.features.sales.data.local.SaleEntity
import com.example.logicore.features.sales.data.local.SaleLineEntity

// EXTRA
import com.example.logicore.features.customers.data.local.CustomerEntity
import com.example.logicore.features.address.data.local.AddressEntity
import com.example.logicore.features.address.data.local.AddressDao


@Database(
    entities = [

        // AUTH
        RoleEntity::class,
        AppUserEntity::class,

        // TENANT
        TenantEntity::class,

        // FLEET
        DriverEntity::class,
        VehicleEntity::class,
        DriverVehicleAssignmentEntity::class,

        // EVENTS
        EventStoreEntity::class,
        CheckpointEntity::class,
        RetryQueueEntity::class,
        DeadLetterEntity::class,

        // PRODUCTS / SUPPLIERS
        ProductEntity::class,
        SupplierEntity::class,
        SupplierProductEntity::class,

        // PROCUREMENT
        PurchaseOrderEntity::class,
        PurchaseOrderLineEntity::class,

        // STOCK
        StockMovementEntity::class,
        StockLocationEntity::class,
        StockProjectionEntity::class,

        // SALES
        SaleEntity::class,
        SaleLineEntity::class,

        // CUSTOMER
        CustomerEntity::class,

        // ADDRESS
        AddressEntity::class
    ],
    version = 10,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    // AUTH
    abstract fun roleDao(): RoleDao
    abstract fun appUserDao(): AppUserDao

    // TENANT
    abstract fun tenantDao(): TenantDao

    // FLEET
    abstract fun driverDao(): DriverDao
    abstract fun vehicleDao(): VehicleDao
    abstract fun driverVehicleDao(): DriverVehicleAssignmentDao

    // EVENTS
    abstract fun eventStoreDao(): EventStoreDao
    abstract fun checkpointDao(): CheckpointDao
    abstract fun retryQueueDao(): RetryQueueDao
    abstract fun deadLetterDao(): DeadLetterDao

    // PRODUCTS / SUPPLIERS
    abstract fun supplierDao(): SupplierDao
    abstract fun supplierProductDao(): SupplierProductDao
    abstract fun productDao(): com.example.logicore.features.products.data.local.ProductDao

    // PROCUREMENT
    abstract fun purchaseOrderDao(): PurchaseOrderDao

    // STOCK
    abstract fun stockDao(): StockDao
    abstract fun stockProjectionDao(): StockProjectionDao

    // SALES
    abstract fun saleDao(): SalesDao

    // ADDRESS
    abstract fun addressDao(): AddressDao
}