package com.ajijul.elnaz.domain.repository.warehouse

import com.ajijul.elnaz.domain.model.WarehouseWithCategoryModel

interface WarehouseRepository {
    suspend fun getWarehouseWithCategories(warehouseId: Long): WarehouseWithCategoryModel?
}