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
import com.ajijul.elnaz.data.local.entity.category.Category
import com.ajijul.elnaz.data.local.entity.Customer
import com.ajijul.elnaz.data.local.entity.Discount
import com.ajijul.elnaz.data.local.entity.DiscountAssignment
import com.ajijul.elnaz.data.local.entity.Inventory
import com.ajijul.elnaz.data.local.entity.Order
import com.ajijul.elnaz.data.local.entity.OrderItem
import com.ajijul.elnaz.data.local.entity.Product
import com.ajijul.elnaz.data.local.entity.Supplier
import com.ajijul.elnaz.data.local.entity.Warehouse
import com.ajijul.elnaz.data.local.entity.WarehousePricing
import com.ajijul.elnaz.data.local.entity.category.CategoryDiscountCrossRef
import com.ajijul.elnaz.data.local.entity.category.CategoryProductCrossRef
import com.ajijul.elnaz.data.local.entity.category.CategorySEO
import com.ajijul.elnaz.data.local.entity.category.CategoryWarehouseCrossRef

@Database(
    entities = [
        Category::class,
        CategoryDiscountCrossRef::class,
        CategoryProductCrossRef::class,
        CategorySEO::class,
        CategoryWarehouseCrossRef::class,
        Customer::class,
        Discount::class,
        DiscountAssignment::class,
        Inventory::class,
        Order::class,
        OrderItem::class,
        Product::class,
        Supplier::class,
        Warehouse::class,
        WarehousePricing::class
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