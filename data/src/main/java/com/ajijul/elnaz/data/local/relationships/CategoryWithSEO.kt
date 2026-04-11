package com.ajijul.elnaz.data.local.relationships

import androidx.room.Embedded
import androidx.room.Relation
import com.ajijul.elnaz.data.local.entity.category.CategoryEntity
import com.ajijul.elnaz.data.local.entity.category.CategorySEOEntity
data class CategoryWithSEO(
    @Embedded val categoryEntity: CategoryEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "categoryId"
    )
    // CRITICAL FIX: Make it nullable! A category might not have SEO details yet.
    val seo: CategorySEOEntity?
)