package com.ajijul.elnaz.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "inventory",
    foreignKeys = [
        ForeignKey(
            entity = Product::class,
            parentColumns = ["id"],
            childColumns = ["productId"],
            onUpdate = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Warehouse::class,
            parentColumns = ["id"],
            childColumns = ["warehouseId"],
            onUpdate = ForeignKey.CASCADE
        )
    ],
    indices = [Index("productId"), Index("warehouseId")]
)
data class Inventory(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val productId: Long,
    val warehouseId: Long,
    val quantity: Int
)

