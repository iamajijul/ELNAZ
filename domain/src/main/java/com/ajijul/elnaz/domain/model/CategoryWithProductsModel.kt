package com.ajijul.elnaz.domain.model

data class CategoryWithProductsModel(
    val category: CategoryModel,
    val products: List<ProductModel>
)
