package com.ajijul.elnaz.data.local.entity.category

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import com.ajijul.elnaz.data.local.entity.Discount

@Entity(
    tableName = "category_discount_cross_ref",
    primaryKeys = ["categoryId", "discountId"],
    foreignKeys = [
        ForeignKey(entity = CategoryEntity::class, parentColumns = ["id"], childColumns = ["categoryId"], onUpdate = ForeignKey.CASCADE),
        ForeignKey(
            entity = Discount::class,
            parentColumns = ["id"],
            childColumns = ["discountId"],
            onUpdate = ForeignKey.CASCADE
        )
    ],
    indices = [Index("categoryId"), Index("discountId")]
)
data class CategoryDiscountCrossRefEntity(
    val categoryId: String,
    val discountId: String,
    val assignedAt: Long = System.currentTimeMillis()
)