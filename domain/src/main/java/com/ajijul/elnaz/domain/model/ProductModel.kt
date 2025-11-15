package com.ajijul.elnaz.domain.model

import com.ajijul.elnaz.domain.model.enums.ProductStatus

data class ProductModel(
    val id: Long,
    val name: String,
    val sku: String,
    val barcode: String,
    val buyPrice: Double,      // cost price
    val salePrice: Double,     // base/global selling price
    val categoryId: Long?,
    val supplierId: Long?,
    val lastUpdated: Long,
    val firstAddedOn: Long,
    val status: ProductStatus,
    val unitId : Long
)
