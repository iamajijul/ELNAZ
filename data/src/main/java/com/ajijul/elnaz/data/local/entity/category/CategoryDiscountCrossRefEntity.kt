package com.ajijul.elnaz.data.local.entity.category

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.ajijul.elnaz.data.local.entity.DiscountEntity
import com.ajijul.elnaz.data.local.entity.utilities.Converters
import com.ajijul.elnaz.domain.model.enums.SyncStatus
import java.util.UUID

@Entity(
    tableName = "category_discount_cross_ref",
    // 1. CRITICAL FIX: Removed primaryKeys = [...] because we are using the UUID below!
    foreignKeys = [
        ForeignKey(
            entity = CategoryEntity::class,
            parentColumns = ["id"],
            childColumns = ["categoryId"],
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE // 2. FIX: Delete link if category dies
        ),
        ForeignKey(
            entity = DiscountEntity::class,
            parentColumns = ["id"],
            childColumns = ["discountId"],
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE // 2. FIX: Delete link if discount dies
        )
    ],
    indices = [
        // 3. CRITICAL FIX: Prevent applying the same discount to the same category twice
        Index(value = ["shopId", "categoryId", "discountId"], unique = true),
        Index("categoryId"),
        Index("discountId")
    ]
)
@TypeConverters(Converters::class)
data class CategoryDiscountCrossRefEntity(
    @PrimaryKey val id: String = UUID.randomUUID().toString(), // The single source of truth for the PK
    val shopId: String,
    val categoryId: String,
    val discountId: String,
    val assignedAt: Long = System.currentTimeMillis(),

    // The SaaS Base
    val lastUpdated: Long = System.currentTimeMillis(),
    val syncState: SyncStatus = SyncStatus.PENDING_INSERT,
    val isDeleted: Boolean = false
)