package com.ajijul.elnaz.data.mapper

import com.ajijul.elnaz.data.local.entity.Warehouse
import com.ajijul.elnaz.data.local.relationships.CategoryWithWarehouse
import com.ajijul.elnaz.data.local.relationships.WarehouseWithCategories
import com.ajijul.elnaz.domain.model.CategoryWithWarehouseModel
import com.ajijul.elnaz.domain.model.WarehouseModel
import com.ajijul.elnaz.domain.model.WarehouseWithCategoryModel


fun Warehouse.toDomain() = WarehouseModel(
    id = id,
    code = code,
    name = name,
    location = location,
    contactNumber = contactNumber,
    managerName = managerName,
    status = status,
    createdAt = createdAt
)

fun WarehouseModel.toEntity() = Warehouse(
    id = id,
    code = code,
    name = name,
    location = location,
    contactNumber = contactNumber,
    managerName = managerName,
    status = status,
    createdAt = createdAt
)

fun WarehouseWithCategories.toDomain(): WarehouseWithCategoryModel {
    return WarehouseWithCategoryModel(
        warehouse = warehouse.toDomain(),
        categories = categories.map { it.toDomain() }
    )
}
