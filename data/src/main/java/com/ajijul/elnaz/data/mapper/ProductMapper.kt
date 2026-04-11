package com.ajijul.elnaz.data.mapper

import com.ajijul.elnaz.data.local.entity.ProductEntity
import com.ajijul.elnaz.domain.model.ProductModel
import java.util.UUID

/**
 * Maps Database Entity to Domain Model (For UI/Business Logic)
 * We drop the "Database Secrets" like shopId and syncState here.
 */
fun ProductEntity.toDomain() = ProductModel(
    id = id,
    shopId = shopId,
    name = name,
    sku = sku,
    barcode = barcode,
    buyPrice = buyPrice,
    salePrice = salePrice,
    mrp = mrp,
    taxRate = taxRate,
    isTaxInclusive = isTaxInclusive,
    hsnSacCode = hsnSacCode,
    categoryId = categoryId,
    supplierId = supplierId,
    unitId = unitId,
    status = status,
    firstAddedOn = firstAddedOn
)

/**
 * Maps Domain Model back to Database Entity (For Saving)
 * We inject the [shopId] and handle the offline sync flags.
 */
fun ProductModel.toEntity() = ProductEntity(
    id = id.ifBlank { UUID.randomUUID().toString() }, // Ensure we always have a UUID
    shopId = shopId, // Injected from the logged-in session
    name = name,
    sku = sku,
    barcode = barcode,
    buyPrice = buyPrice,
    salePrice = salePrice,
    mrp = mrp,
    taxRate = taxRate,
    isTaxInclusive = isTaxInclusive,
    hsnSacCode = hsnSacCode,
    categoryId = categoryId,
    supplierId = supplierId,
    unitId = unitId,
    status = status,

    // SaaS Base & Sync Logic
    firstAddedOn = firstAddedOn,
    lastUpdated = System.currentTimeMillis(),
    // syncState and isDeleted will use their default Entity values
)