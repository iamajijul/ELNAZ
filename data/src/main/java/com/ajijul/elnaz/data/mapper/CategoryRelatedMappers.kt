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

fun CategoryEntity.toDomain() = CategoryModel(
    id = id,
    name = name,
    description = description,
    categoryImage = categoryImage,
    parentId = parentId,
    status = status,
    popularityScore = popularityScore,
    createdAt = createdAt
)

fun CategoryModel.toEntity() = CategoryEntity(
    id = id,
    name = name,
    description = description,
    categoryImage = categoryImage,
    parentId = parentId,
    status = status,
    popularityScore = popularityScore,
    createdAt = createdAt
)

fun CategorySEOEntity.toDomain() = CategorySeoModel(
    id = id,
    categoryId = categoryId,
    seoTitle = seoTitle,
    seoDescription = seoDescription,
    seoSlug = seoSlug
)

fun CategorySeoModel.toEntity() = CategorySEOEntity(
    id = id,
    categoryId = categoryId,
    seoTitle = seoTitle,
    seoDescription = seoDescription,
    seoSlug = seoSlug
)

fun CategoryWithProducts.toDomain(): CategoryWithProductsModel {
    return CategoryWithProductsModel(
        category = categoryEntity.toDomain(),
        products = products.map { it.toDomain() }
    )
}

fun CategoryWithWarehouse.toDomain(): CategoryWithWarehouseModel {
    return CategoryWithWarehouseModel(
        category = categoryEntity.toDomain(),
        warehouses = warehouses.map { it.toDomain() }
    )
}


fun CategoryWithDiscount.toDomain(): CategoryWithDiscountsModel {
    return CategoryWithDiscountsModel(
        category = categoryEntity.toDomain(),
        discounts = discounts.map { it.toDomain() }
    )
}

fun CategoryWithSEO.toDomain(): CategoryWithSEOModel {
    return CategoryWithSEOModel(
        category = categoryEntity.toDomain(),
        seo = seo.toDomain()
    )
}