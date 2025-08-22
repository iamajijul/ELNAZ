package com.ajijul.elnaz.domain.model

data class DiscountAssignmentModel(
    val discountId: String,
    val productId: String?,
    val categoryId: String?,
    val warehouseId: String?
)
