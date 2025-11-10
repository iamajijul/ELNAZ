package com.ajijul.elnaz.data.local.relationships

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.ajijul.elnaz.data.local.entity.Discount
import com.ajijul.elnaz.data.local.entity.category.Category
import com.ajijul.elnaz.data.local.entity.category.CategoryDiscountCrossRef

data class CategoryWithDiscount(
    @Embedded val category: Category,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(CategoryDiscountCrossRef::class,
            parentColumn = "categoryId",
            entityColumn = "discountId")
    ) val discounts: List<Discount>
)
