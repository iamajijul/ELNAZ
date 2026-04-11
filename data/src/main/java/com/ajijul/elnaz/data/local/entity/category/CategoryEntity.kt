package com.ajijul.elnaz.data.local.entity.category

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.ajijul.elnaz.data.local.entity.utilities.Converters
import com.ajijul.elnaz.domain.model.enums.CategoryStatus
import com.ajijul.elnaz.domain.model.enums.SyncStatus
import java.util.UUID

@Entity(
    tableName = "category",
    indices = [
        // CRITICAL FIX: Allows Shop A and Shop B to both have an "Electronics" category!
        Index(value = ["shopId", "name"], unique = true)
    ]
)
@TypeConverters(Converters::class)
data class CategoryEntity(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val shopId: String,
    val name: String,
    val description: String = "",
    val categoryImage: String = "",
    val parentId: String? = null, // Correctly left as a String? to link to other UUIDs!
    val status: CategoryStatus = CategoryStatus.ACTIVE,
    val createdAt: Long = System.currentTimeMillis(),
    val popularityScore: Int = 0,

    // The SaaS Base
    val lastUpdated: Long = System.currentTimeMillis(),
    val syncState: SyncStatus = SyncStatus.PENDING_INSERT,
    val isDeleted: Boolean = false
)