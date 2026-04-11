package com.ajijul.elnaz.data.mapper

import com.ajijul.elnaz.data.local.entity.category.CategoryEntity
import com.ajijul.elnaz.data.local.entity.category.CategorySEOEntity
import com.ajijul.elnaz.data.local.relationships.CategoryWithDiscount
import com.ajijul.elnaz.data.local.relationships.CategoryWithProducts
import com.ajijul.elnaz.data.local.relationships.CategoryWithSEO
import com.ajijul.elnaz.data.local.relationships.CategoryWithWarehouse
import com.ajijul.elnaz.domain.model.CategoryModel
import com.ajijul.elnaz.domain.model.CategorySeoModel
import com.ajijul.elnaz.domain.model.CategoryWithDiscountsModel
import com.ajijul.elnaz.domain.model.CategoryWithProductsModel
import com.ajijul.elnaz.domain.model.CategoryWithSEOModel
import com.ajijul.elnaz.domain.model.CategoryWithWarehouseModel
import java.util.UUID

// --- CATEGORY MAPPING ---

fun CategoryEntity.toDomain() = CategoryModel(
    id = id,
    shopId = shopId,
    name = name,
    description = description,
    categoryImage = categoryImage,
    parentId = parentId,
    status = status,
    popularityScore = popularityScore,
    createdAt = createdAt
)

fun CategoryModel.toEntity() = CategoryEntity(
    id = id.ifBlank { UUID.randomUUID().toString() },
    shopId = shopId, // Internal mapping from model
    name = name,
    description = description,
    categoryImage = categoryImage,
    parentId = parentId,
    status = status,
    popularityScore = popularityScore,
    createdAt = createdAt,
    lastUpdated = System.currentTimeMillis()
)

// --- SEO MAPPING ---

fun CategorySEOEntity.toDomain() = CategorySeoModel(
    id = id,
    shopId = shopId,
    categoryId = categoryId,
    seoTitle = seoTitle,
    seoDescription = seoDescription,
    seoSlug = seoSlug
)

fun CategorySeoModel.toEntity() = CategorySEOEntity(
    id = id.ifBlank { UUID.randomUUID().toString() },
    shopId = shopId,
    categoryId = categoryId,
    seoTitle = seoTitle,
    seoDescription = seoDescription,
    seoSlug = seoSlug,
    lastUpdated = System.currentTimeMillis()
)

// --- RELATIONSHIP MAPPERS ---

fun CategoryWithSEO.toDomain(): CategoryWithSEOModel {
    return CategoryWithSEOModel(
        category = categoryEntity.toDomain(),
        // Soft-delete safety check
        seo = if (seo != null && !seo.isDeleted) seo.toDomain() else null
    )
}

/**
 * NOTE: For Many-to-Many (Products, Warehouses, Discounts),
 * we use these mappers ONLY if we use Room's @Relation.
 * If using custom DAO JOINs (Recommended), these are handled in the Repository.
 */

fun CategoryWithProducts.toDomain(): CategoryWithProductsModel {
    return CategoryWithProductsModel(
        category = categoryEntity.toDomain(),
        products = productEntities.map { it.toDomain() }
    )
}

fun CategoryWithWarehouse.toDomain(): CategoryWithWarehouseModel {
    return CategoryWithWarehouseModel(
        category = categoryEntity.toDomain(),
        warehouses = warehouseEntities.map { it.toDomain() }
    )
}

fun CategoryWithDiscount.toDomain(): CategoryWithDiscountsModel {
    return CategoryWithDiscountsModel(
        category = categoryEntity.toDomain(),
        discounts = discountEntities.map { it.toDomain() }
    )
}