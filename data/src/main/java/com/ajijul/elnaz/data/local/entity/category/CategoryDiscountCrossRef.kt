package com.ajijul.elnaz.data.local.entity.category

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import com.ajijul.elnaz.data.local.entity.Discount
import com.ajijul.elnaz.data.local.entity.Warehouse

@Entity(
    tableName = "category_discount_cross_ref",
    primaryKeys = ["categoryId", "discountId"],
    foreignKeys = [
        ForeignKey(entity = Category::class, parentColumns = ["id"], childColumns = ["categoryId"], onUpdate = ForeignKey.CASCADE),
        ForeignKey(
            entity = Discount::class,
            parentColumns = ["id"],
            childColumns = ["discountId"],
            onUpdate = ForeignKey.CASCADE
        )
    ],
    indices = [Index("categoryId"), Index("discountId")]
)
data class CategoryDiscountCrossRef(
    val categoryId: Long,
    val discountId: Long,
    val assignedAt: Long = System.currentTimeMillis()
)