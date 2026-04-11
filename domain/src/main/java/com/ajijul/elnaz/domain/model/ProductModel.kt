package com.ajijul.elnaz.domain.model

import com.ajijul.elnaz.domain.model.enums.ProductStatus

/**
 * The Clean Domain Model for the UI.
 * Notice: No shopId, no syncState, no lastUpdated.
 */
data class ProductModel(
    val id: String,
    val shopId: String,
    val name: String,
    val sku: String,
    val barcode: String,

    // Pricing & Tax (Essential for Vyapar-style billing)
    val buyPrice: Double,
    val salePrice: Double,
    val mrp: Double,
    val taxRate: Double,
    val isTaxInclusive: Boolean,
    val hsnSacCode: String? = null,

    // Relationships
    val categoryId: String?,
    val supplierId: String?,
    val unitId: String,

    // Metadata the UI might actually use
    val status: ProductStatus = ProductStatus.ACTIVE,
    val firstAddedOn: Long = System.currentTimeMillis()
)