package com.ajijul.elnaz.data.mapper

import com.ajijul.elnaz.data.local.entity.Discount
import com.ajijul.elnaz.domain.model.DiscountModel
import com.ajijul.elnaz.domain.model.enums.DiscountStatus
import com.ajijul.elnaz.domain.model.enums.DiscountType

fun Discount.toDomain() = DiscountModel(
    id = id,
    code = code,
    name = name,
    description = description,
    discountType = discountType,
    discountValue = discountValue,
    maxDiscountAmount = maxDiscountAmount,
    minOrderAmount = minOrderAmount,
    startDate = startDate,
    endDate = endDate,
    status = status,
    priority = priority
)

fun DiscountModel.toEntity() = Discount(
    id = id,
    code = code,
    name = name,
    description = description,
    discountType = discountType,
    discountValue = discountValue,
    maxDiscountAmount = maxDiscountAmount,
    minOrderAmount = minOrderAmount,
    startDate = startDate,
    endDate = endDate,
    status = status,
    priority = priority
)