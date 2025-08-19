package com.ajijul.elnaz.data.mapper

import com.ajijul.elnaz.data.local.entity.Product
import com.ajijul.elnaz.domain.model.ProductModel

fun Product.toDomain() = ProductModel(
    id = id,
    name = name,
    barcode = barcode,
    buyPrice = buyPrice,
    salePrice = salePrice,
    categoryId = categoryId,
    supplierId = supplierId,
    lastUpdated = lastUpdated
)

fun ProductModel.toProductEntity() = Product(
    id = id,
    name = name,
    barcode = barcode,
    buyPrice = buyPrice,
    salePrice = salePrice,
    categoryId = categoryId,
    supplierId = supplierId,
    lastUpdated = lastUpdated
)