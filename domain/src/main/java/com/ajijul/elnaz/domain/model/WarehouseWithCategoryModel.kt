package com.ajijul.elnaz.domain.model

data class WarehouseWithCategoryModel(
    val warehouse: WarehouseModel,
    val categories: List<CategoryModel>
)
