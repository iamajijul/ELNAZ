package com.ajijul.elnaz.data.local.entity.category

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.ajijul.elnaz.data.local.entity.ProductEntity
import com.ajijul.elnaz.data.local.entity.utilities.Converters
import com.ajijul.elnaz.domain.model.enums.SyncStatus
import java.util.UUID

@Entity(
    tableName = "category_product_cross_ref",
    // 1. CRITICAL FIX: Removed primaryKeys array so Room respects our UUID below
    foreignKeys = [
        ForeignKey(
            entity = CategoryEntity::class,
            parentColumns = ["id"],
            childColumns = ["categoryId"],
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE // 2. FIX: Delete link if category dies
        ),
        ForeignKey(
            entity = ProductEntity::class,
            parentColumns = ["id"],
            childColumns = ["productId"],
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE // 2. FIX: Delete link if product dies
        )
    ],
    indices = [
        // 3. CRITICAL FIX: Prevent putting the same product in the same category twice!
        Index(value = ["shopId", "categoryId", "productId"], unique = true),
        Index("categoryId"),
        Index("productId")
    ]
)
@TypeConverters(Converters::class)
data class CategoryProductCrossRefEntity(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val shopId: String,
    val categoryId: String,
    val productId: String,

    // The SaaS Base
    val lastUpdated: Long = System.currentTimeMillis(),
    val syncState: SyncStatus = SyncStatus.PENDING_INSERT,
    val isDeleted: Boolean = false
)