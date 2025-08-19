package com.ajijul.elnaz.domain.model

data class ProductModel(
    val id: Long = 0,
    val name: String,
    val barcode: String,
    val buyPrice: Double,      // cost price
    val salePrice: Double,     // base/global selling price
    val categoryId: Long?,
    val supplierId: Long?,
    val lastUpdated: Long,
    val firstAddedOn: Long = System.currentTimeMillis()
)
