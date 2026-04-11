package com.ajijul.elnaz.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.ajijul.elnaz.data.local.entity.category.CategoryEntity
import com.ajijul.elnaz.data.local.entity.utilities.Converters
import com.ajijul.elnaz.domain.model.enums.SyncStatus
import java.util.UUID

@Entity(
    tableName = "discount_assignment",
    foreignKeys = [
        ForeignKey(
            entity = DiscountEntity::class,
            parentColumns = ["id"],
            childColumns = ["discountId"],
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE // FIX: If discount dies, this assignment dies
        ),
        ForeignKey(
            entity = ProductEntity::class,
            parentColumns = ["id"],
            childColumns = ["productId"],
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE // FIX: If product dies, remove its discount link
        ),
        ForeignKey(
            entity = CategoryEntity::class,
            parentColumns = ["id"],
            childColumns = ["categoryId"],
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = WarehouseEntity::class,
            parentColumns = ["id"],
            childColumns = ["warehouseId"],
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        // Basic indices for fast querying
        Index("shopId"), Index("discountId"), Index("productId"), Index("categoryId"), Index("warehouseId"),

        // CRITICAL FIX: Prevent applying the exact same discount to the exact same item twice!
        Index(value = ["shopId", "discountId", "productId"], unique = true),
        Index(value = ["shopId", "discountId", "categoryId"], unique = true),
        Index(value = ["shopId", "discountId", "warehouseId"], unique = true)
    ]
)
@TypeConverters(Converters::class)
data class DiscountAssignment(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val shopId: String,
    val discountId: String,

    // It can apply to ONE of these:
    val productId: String?,
    val categoryId: String?,
    val warehouseId: String?,

    // The SaaS Base
    val lastUpdated: Long = System.currentTimeMillis(),
    val syncState: SyncStatus = SyncStatus.PENDING_INSERT,
    val isDeleted: Boolean = false
)