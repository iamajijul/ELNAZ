package com.ajijul.elnaz.data.local.relationships

import androidx.room.Embedded
import androidx.room.Relation
import com.ajijul.elnaz.data.local.entity.Product
import com.ajijul.elnaz.data.local.entity.category.Category

data class CategoryWithProducts(
    @Embedded val category: Category,
    @Relation(
        parentColumn = "id",
        entityColumn = "categoryId"
    ) val products: List<Product>
)
