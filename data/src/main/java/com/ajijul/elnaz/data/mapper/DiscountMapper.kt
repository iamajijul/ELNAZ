package com.ajijul.elnaz.data.mapper

import com.ajijul.elnaz.data.local.entity.DiscountEntity
import com.ajijul.elnaz.domain.model.DiscountModel

fun DiscountEntity.toDomain() = DiscountModel(
    id = id,
    shopId = shopId,
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

fun DiscountModel.toEntity() = DiscountEntity(
    id = id,
    shopId = shopId,
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