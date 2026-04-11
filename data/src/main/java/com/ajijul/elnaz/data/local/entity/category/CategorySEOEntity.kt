package com.ajijul.elnaz.data.local.entity.category

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.ajijul.elnaz.data.local.entity.utilities.Converters
import com.ajijul.elnaz.domain.model.enums.SyncStatus
import java.util.UUID

@Entity(
    tableName = "category_seo",
    foreignKeys = [
        ForeignKey(
            entity = CategoryEntity::class,
            parentColumns = ["id"],
            childColumns = ["categoryId"],
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE // FIX 2: Delete SEO if category dies
        )
    ],
    indices = [
        // FIX 3: Enforce strict One-to-One relationship per shop!
        Index(value = ["shopId", "categoryId"], unique = true)
    ]
)
@TypeConverters(Converters::class)
data class CategorySEOEntity(
    // FIX 1: Strings (UUIDs) instead of Longs
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val shopId: String,
    val categoryId: String,

    val seoTitle: String?,
    val seoDescription: String?,
    val seoSlug: String?,

    // The SaaS Base
    val lastUpdated: Long = System.currentTimeMillis(),
    val syncState: SyncStatus = SyncStatus.PENDING_INSERT,
    val isDeleted: Boolean = false
)