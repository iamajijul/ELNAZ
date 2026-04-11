package com.ajijul.elnaz.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.ajijul.elnaz.domain.model.enums.SyncStatus
import java.util.UUID

@Entity(
    tableName = "warehouse_pricing",
    foreignKeys = [
        ForeignKey(
            entity = WarehouseEntity::class,
            parentColumns = ["id"],
            childColumns = ["warehouseId"],
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE // If the warehouse is deleted, delete its custom pricing
        ),
        ForeignKey(
            entity = ProductEntity::class,
            parentColumns = ["id"],
            childColumns = ["productId"],
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE // If the product is deleted, delete its custom pricing
        )
    ],
    indices = [
        // CRITICAL FIX: Ensures a specific product can only have ONE override price per warehouse
        Index(value = ["shopId", "warehouseId", "productId"], unique = true),
        Index(value = ["shopId", "productId"]),
        Index(value = ["shopId", "warehouseId"])
    ]
)
data class WarehousePricingEntity(
    // 1. Machine ID (UUID)
    @PrimaryKey val id: String = UUID.randomUUID().toString(),

    // 2. Multi-Tenant Router
    val shopId: String,

    // 3. Foreign Keys (Must be Strings to match the parent tables)
    val warehouseId: String,
    val productId: String,

    // 4. The Override Value
    val overrideSalePrice: Double?, // optional warehouse-specific price

    // 5. SaaS Base & Sync Engine
    val lastUpdated: Long = System.currentTimeMillis(),
    val syncState: SyncStatus = SyncStatus.PENDING_INSERT,
    val isDeleted: Boolean = false // Soft delete
)