package com.ajijul.elnaz.data.mapper

import com.ajijul.elnaz.data.local.entity.ItemEntity
import com.ajijul.elnaz.domain.model.Item

fun ItemEntity.toDomainItem() = Item(
    id = id,
    name = name,
    barcode = barcode,
    quantity = quantity,
    price = price,
    lastUpdated = lastUpdated,
    createdAt = createdAt,
    location = location
)

fun Item.toDataItemEntity() = ItemEntity(
    id = id,
    name = name,
    barcode = barcode,
    quantity = quantity,
    price = price,
    lastUpdated = lastUpdated,
    createdAt = createdAt,
    location = location
)