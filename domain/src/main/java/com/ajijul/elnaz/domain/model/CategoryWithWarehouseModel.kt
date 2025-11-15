package com.ajijul.elnaz.domain.model

data class CategoryWithWarehouseModel(
    val category: CategoryModel,
    val warehouses: List<WarehouseModel>
)
