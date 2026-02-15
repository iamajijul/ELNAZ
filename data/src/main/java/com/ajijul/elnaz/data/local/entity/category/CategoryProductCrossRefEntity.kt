package com.ajijul.elnaz.data.local.entity.category

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import com.ajijul.elnaz.data.local.entity.Product

@Entity(
    tableName = "category_product_cross_ref",
    primaryKeys = ["categoryId", "productId"],
    foreignKeys = [
        ForeignKey(entity = CategoryEntity::class, parentColumns = ["id"], childColumns = ["categoryId"]),
        ForeignKey(
            entity = Product::class,
            parentColumns = ["id"],
            childColumns = ["productId"],
            onUpdate = ForeignKey.CASCADE
        )
    ],
    indices = [Index("categoryId"), Index("productId")]
)
data class CategoryProductCrossRefEntity(
    val categoryId: String,
    val productId: String
)
