package com.ajijul.elnaz.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.NO_ACTION
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "product",
    foreignKeys = [
        ForeignKey(
            entity = Category::class,
            parentColumns = ["id"],
            childColumns = ["categoryId"],
            onDelete = NO_ACTION
        ),
        ForeignKey(
            entity = Supplier::class,
            parentColumns = ["id"],
            childColumns = ["supplierId"],
            onDelete = NO_ACTION
        )
    ],
    indices = [Index("categoryId"), Index("supplierId")]
)
data class Product(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val barcode: String,
    val buyPrice: Double,      // cost price
    val salePrice: Double,     // base/global selling price
    val categoryId: Long?,
    val supplierId: Long?,
    val lastUpdated: Long,
    val firstAddedOn: Long = System.currentTimeMillis()
)


