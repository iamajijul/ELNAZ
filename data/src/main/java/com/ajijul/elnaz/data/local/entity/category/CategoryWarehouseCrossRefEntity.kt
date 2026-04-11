package com.ajijul.elnaz.data.local.entity.category

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.ajijul.elnaz.data.local.entity.WarehouseEntity
import com.ajijul.elnaz.data.local.entity.utilities.Converters
import com.ajijul.elnaz.domain.model.enums.SyncStatus
import java.util.UUID

@Entity(
    tableName = "category_warehouse_cross_ref",
    // 1. CRITICAL FIX: Removed primaryKeys array
    foreignKeys = [
        ForeignKey(
            entity = CategoryEntity::class,
            parentColumns = ["id"],
            childColumns = ["categoryId"],
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE // 2. FIX: Delete link if category dies
        ),
        ForeignKey(
            entity = WarehouseEntity::class,
            parentColumns = ["id"],
            childColumns = ["warehouseId"],
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE // 2. FIX: Delete link if warehouse dies
        )
    ],
    indices = [
        // 3. CRITICAL FIX: Prevent putting the same category in the same warehouse twice!
        Index(value = ["shopId", "categoryId", "warehouseId"], unique = true),
        Index("categoryId"),
        Index("warehouseId")
    ]
)
@TypeConverters(Converters::class)
data class CategoryWarehouseCrossRefEntity(
    // 4. FIX: Added the default UUID generator
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val shopId: String,
    val categoryId: String,
    val warehouseId: String,

    // The SaaS Base
    val lastUpdated: Long = System.currentTimeMillis(),
    val syncState: SyncStatus = SyncStatus.PENDING_INSERT,
    val isDeleted: Boolean = false
)