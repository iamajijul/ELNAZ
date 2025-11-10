package com.ajijul.elnaz.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.NO_ACTION
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.ajijul.elnaz.data.local.entity.category.Category
import com.ajijul.elnaz.data.local.entity.utilities.Converters
import com.ajijul.elnaz.domain.model.enums.ProductStatus

@Entity(
    tableName = "product",
    foreignKeys = [
        ForeignKey(
            entity = Category::class,
            parentColumns = ["id"],
            childColumns = ["categoryId"],
            onUpdate = ForeignKey.CASCADE

        ),
        ForeignKey(
            entity = Supplier::class,
            parentColumns = ["id"],
            childColumns = ["supplierId"],
            onUpdate = ForeignKey.CASCADE
        )
    ],
    indices = [Index("categoryId"), Index("supplierId"), Index("name")]
)
@TypeConverters(Converters::class)
data class Product(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val sku: String,
    val barcode: String,
    val buyPrice: Double,      // cost price
    val salePrice: Double,     // base/global selling price
    val categoryId: Long?,
    val supplierId: Long?,
    val lastUpdated: Long,
    val firstAddedOn: Long = System.currentTimeMillis(),
    val status: ProductStatus = ProductStatus.ACTIVE,
    )


