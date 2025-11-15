package com.ajijul.elnaz.domain.model

import com.ajijul.elnaz.domain.model.enums.DiscountStatus
import com.ajijul.elnaz.domain.model.enums.DiscountType

data class DiscountModel(
    val id: Long = 0,

    val code: String,
    val name: String,
    val description: String? = null,

    val discountType: DiscountType,                           // percentage or fixed amount
    val discountValue: Double,                                // 10% or ₹50
    val maxDiscountAmount: Double? = null,                    // for "up to ₹100"
    val minOrderAmount: Double? = null,                       // optional threshold

    val startDate: Long,
    val endDate: Long?,
    val status: DiscountStatus = DiscountStatus.ACTIVE,
    val priority: Int = 0      // higher = takes precedence
)
