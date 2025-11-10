package com.ajijul.elnaz.data.local.relationships

import androidx.room.Embedded
import androidx.room.Relation
import com.ajijul.elnaz.data.local.entity.category.Category
import com.ajijul.elnaz.data.local.entity.category.CategorySEO

data class CategoryWithSEO(
    @Embedded val category: Category,
    @Relation(
        parentColumn = "id",
        entityColumn = "categoryId"
    )
    val seo: CategorySEO?
)