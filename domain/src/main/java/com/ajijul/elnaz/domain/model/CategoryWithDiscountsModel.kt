package com.ajijul.elnaz.domain.model

data class CategoryWithDiscountsModel(
    val category: CategoryModel,
    val discounts: List<DiscountModel>
)
