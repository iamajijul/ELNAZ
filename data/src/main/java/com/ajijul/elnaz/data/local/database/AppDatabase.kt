package com.ajijul.elnaz.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ajijul.elnaz.data.local.dao.CategoryDao
import com.ajijul.elnaz.data.local.dao.CustomerDao
import com.ajijul.elnaz.data.local.dao.DiscountAssignmentDao
import com.ajijul.elnaz.data.local.dao.DiscountDao
import com.ajijul.elnaz.data.local.dao.InventoryDao
import com.ajijul.elnaz.data.local.dao.OrderDao
import com.ajijul.elnaz.data.local.dao.OrderItemDao
import com.ajijul.elnaz.data.local.dao.ProductDao
import com.ajijul.elnaz.data.local.dao.SupplierDao
import com.ajijul.elnaz.data.local.dao.WarehouseDao
import com.ajijul.elnaz.data.local.dao.WarehousePricingDao
import com.ajijul.elnaz.data.local.entity.CustomerEntity
import com.ajijul.elnaz.data.local.entity.DiscountAssignment
import com.ajijul.elnaz.data.local.entity.DiscountEntity
import com.ajijul.elnaz.data.local.entity.InventoryEntity
import com.ajijul.elnaz.data.local.entity.InvoiceEntity
import com.ajijul.elnaz.data.local.entity.InvoiceItemEntity
import com.ajijul.elnaz.data.local.entity.ProductEntity
import com.ajijul.elnaz.data.local.entity.SupplierEntity
import com.ajijul.elnaz.data.local.entity.UnitOfMeasureEntity
import com.ajijul.elnaz.data.local.entity.WarehouseEntity
import com.ajijul.elnaz.data.local.entity.WarehousePricingEntity
import com.ajijul.elnaz.data.local.entity.category.CategoryDiscountCrossRefEntity
import com.ajijul.elnaz.data.local.entity.category.CategoryEntity
import com.ajijul.elnaz.data.local.entity.category.CategoryProductCrossRefEntity
import com.ajijul.elnaz.data.local.entity.category.CategorySEOEntity
import com.ajijul.elnaz.data.local.entity.category.CategoryWarehouseCrossRefEntity

@Database(
    entities = [
        CategoryEntity::class,
        CategoryDiscountCrossRefEntity::class,
        CategoryProductCrossRefEntity::class,
        CategorySEOEntity::class,
        CategoryWarehouseCrossRefEntity::class,
        CustomerEntity::class,
        DiscountEntity::class,
        DiscountAssignment::class,
        InventoryEntity::class,
        InvoiceEntity::class,
        InvoiceItemEntity::class,
        ProductEntity::class,
        SupplierEntity::class,
        UnitOfMeasureEntity::class,
        WarehouseEntity::class,
        WarehousePricingEntity::class
    ], version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
    abstract fun customerDao(): CustomerDao
    abstract fun discountAssignmentDao(): DiscountAssignmentDao
    abstract fun discountDao(): DiscountDao
    abstract fun inventoryDao(): InventoryDao
    abstract fun orderDao(): OrderDao
    abstract fun orderItemDao(): OrderItemDao
    abstract fun productDao(): ProductDao
    abstract fun supplierDao(): SupplierDao
    abstract fun warehouseDao(): WarehouseDao
    abstract fun warehousePricingDao(): WarehousePricingDao
}