package com.ajijul.elnaz.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "discount_assignment",
    foreignKeys = [
        ForeignKey(entity = Discount::class, parentColumns = ["id"], childColumns = ["discountId"]),
        ForeignKey(entity = Product::class, parentColumns = ["id"], childColumns = ["productId"]),
        ForeignKey(entity = Category::class, parentColumns = ["id"], childColumns = ["categoryId"]),
        ForeignKey(
            entity = Warehouse::class,
            parentColumns = ["id"],
            childColumns = ["warehouseId"]
        )
    ],
    indices = [Index("discountId"), Index("productId"), Index("categoryId"), Index("warehouseId"),
        Index(value = ["productId", "categoryId", "warehouseId"])]
)
data class DiscountAssignment(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val discountId: Long,
    val productId: Long?,     // nullable → specific product
    val categoryId: Long?,    // nullable → category-wide
    val warehouseId: Long?    // nullable → warehouse-specific
)

