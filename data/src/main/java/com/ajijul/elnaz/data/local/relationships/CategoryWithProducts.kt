package com.ajijul.elnaz.data.local.relationships

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.ajijul.elnaz.data.local.entity.ProductEntity
import com.ajijul.elnaz.data.local.entity.category.CategoryEntity
import com.ajijul.elnaz.data.local.entity.category.CategoryProductCrossRefEntity

data class CategoryWithProducts(
    @Embedded val categoryEntity: CategoryEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            CategoryProductCrossRefEntity::class,
            parentColumn = "categoryId",
            entityColumn = "productId"
        )
    ) val productEntities: List<ProductEntity>
)
