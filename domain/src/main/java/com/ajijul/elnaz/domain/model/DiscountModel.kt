package com.ajijul.elnaz.domain.model

import com.ajijul.elnaz.domain.model.enums.DiscountType

data class DiscountModel(
    val id: String,
    val name: String,
    val discountType: DiscountType,
    val value: Double,
    val startDate: Long?,
    val endDate: Long?,
    val isActive: Boolean
)
