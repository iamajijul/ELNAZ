package com.ajijul.elnaz.data.local.entity.category

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import com.ajijul.elnaz.data.local.entity.Product
import com.ajijul.elnaz.data.local.entity.Warehouse

@Entity(
    tableName = "category_product_cross_ref",
    primaryKeys = ["categoryId", "productId"],
    foreignKeys = [
        ForeignKey(entity = Category::class, parentColumns = ["id"], childColumns = ["categoryId"]),
        ForeignKey(
            entity = Product::class,
            parentColumns = ["id"],
            childColumns = ["productId"],
            onUpdate = ForeignKey.CASCADE
        )
    ],
    indices = [Index("categoryId"), Index("productId")]
)
data class CategoryProductCrossRef(
    val categoryId: Long,
    val productId: Long
)
