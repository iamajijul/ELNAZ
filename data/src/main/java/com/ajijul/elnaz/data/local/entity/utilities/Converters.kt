package com.ajijul.elnaz.data.local.entity.utilities

import androidx.room.TypeConverter
import com.ajijul.elnaz.domain.model.enums.CategoryStatus
import com.ajijul.elnaz.domain.model.enums.DiscountStatus
import com.ajijul.elnaz.domain.model.enums.DiscountType
import com.ajijul.elnaz.domain.model.enums.ProductStatus
import com.ajijul.elnaz.domain.model.enums.SupplierStatus
import com.ajijul.elnaz.domain.model.enums.WarehouseStatus

class Converters {

    // --- Category ---
    @TypeConverter
    fun fromCategoryStatus(value: CategoryStatus): String = value.name
    @TypeConverter
    fun toCategoryStatus(value: String): CategoryStatus = CategoryStatus.valueOf(value)


    // --- Product ---
    @TypeConverter
    fun fromProductStatus(value: ProductStatus): String = value.name
    @TypeConverter
    fun toProductStatus(value: String): ProductStatus = ProductStatus.valueOf(value)


    // --- Discount ---
    @TypeConverter
    fun fromDiscountStatus(value: DiscountStatus): String = value.name
    @TypeConverter
    fun toDiscountStatus(value: String): DiscountStatus = DiscountStatus.valueOf(value)
    @TypeConverter
    fun fromDiscountType(value: DiscountType): String = value.name
    @TypeConverter
    fun toDiscountType(value: String): DiscountType = DiscountType.valueOf(value)


    // --- Warehouse ---
    @TypeConverter
    fun fromWarehouseStatus(value: WarehouseStatus): String = value.name
    @TypeConverter
    fun toWarehouseStatus(value: String): WarehouseStatus = WarehouseStatus.valueOf(value)


    // --- Supplier ---
    @TypeConverter
    fun fromSupplierStatus(value: SupplierStatus): String = value.name
    @TypeConverter
    fun toSupplierStatus(value: String): SupplierStatus = SupplierStatus.valueOf(value)

}