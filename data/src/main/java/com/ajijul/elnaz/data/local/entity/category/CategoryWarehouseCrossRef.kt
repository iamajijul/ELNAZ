package com.ajijul.elnaz.data.local.entity.category

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import com.ajijul.elnaz.data.local.entity.Discount
import com.ajijul.elnaz.data.local.entity.Warehouse

@Entity(
    tableName = "category_warehouse_cross_ref",
    primaryKeys = ["categoryId", "warehouseId"],
    foreignKeys = [
        ForeignKey(entity = Category::class, parentColumns = ["id"], childColumns = ["categoryId"]),
        ForeignKey(
            entity = Warehouse::class,
            parentColumns = ["id"],
            childColumns = ["warehouseId"],
            onUpdate = ForeignKey.CASCADE
        )
    ],
    indices = [Index("categoryId"), Index("warehouseId")]
)
data class CategoryWarehouseCrossRef(
    val categoryId: Long,
    val warehouseId: Long
)
