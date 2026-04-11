package com.ajijul.elnaz.data.local.relationships

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.ajijul.elnaz.data.local.entity.DiscountEntity
import com.ajijul.elnaz.data.local.entity.category.CategoryEntity
import com.ajijul.elnaz.data.local.entity.category.CategoryDiscountCrossRefEntity

data class CategoryWithDiscount(
    @Embedded val categoryEntity: CategoryEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(CategoryDiscountCrossRefEntity::class,
            parentColumn = "categoryId",
            entityColumn = "discountId")
    ) val discountEntities: List<DiscountEntity>
)
