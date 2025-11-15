package com.ajijul.elnaz.data.mapper

import com.ajijul.elnaz.data.local.entity.Product
import com.ajijul.elnaz.domain.model.ProductModel

fun Product.toDomain() = ProductModel(
    id = id,
    name = name,
    sku = sku,
    barcode = barcode,
    buyPrice = buyPrice,
    salePrice = salePrice,
    categoryId = categoryId,
    supplierId = supplierId,
    lastUpdated = lastUpdated,
    firstAddedOn = firstAddedOn,
    status = status,
    unitId = unitId
)

fun ProductModel.toProductEntity() = Product(
    id = id,
    name = name,
    sku = sku,
    barcode = barcode,
    buyPrice = buyPrice,
    salePrice = salePrice,
    categoryId = categoryId,
    supplierId = supplierId,
    lastUpdated = lastUpdated,
    firstAddedOn = firstAddedOn,
    status = status,
    unitId = unitId
)