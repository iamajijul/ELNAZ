package com.ajijul.elnaz.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.ajijul.elnaz.data.local.entity.utilities.Converters
import com.ajijul.elnaz.domain.model.enums.SyncStatus
import java.util.UUID

@Entity(
    tableName = "inventory",
    foreignKeys = [
        ForeignKey(
            entity = ProductEntity::class,
            parentColumns = ["id"],
            childColumns = ["productId"],
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.RESTRICT // FINANCIAL SAFEGUARD: Don't delete products that have stock!
        ),
        ForeignKey(
            entity = WarehouseEntity::class,
            parentColumns = ["id"],
            childColumns = ["warehouseId"],
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.RESTRICT // Don't delete a warehouse if it currently holds stock!
        )
    ],
    indices = [
        // CRITICAL FIX: Ensures a product only has ONE inventory row per warehouse!
        Index(value = ["shopId", "productId", "warehouseId"], unique = true),
        Index(value = ["shopId", "productId"]),
        Index(value = ["shopId", "warehouseId"])
    ]
)
@TypeConverters(Converters::class)
data class InventoryEntity(
    // 1. Machine ID
    @PrimaryKey val id: String = UUID.randomUUID().toString(),

    // 2. SaaS Router
    val shopId: String,

    // 3. Foreign Keys (FIX: Changed from Long to String to match parent UUIDs)
    val productId: String,
    val warehouseId: String,

    // 4. The Stock Level
    val quantity: Double, // Double is perfect here because they might sell 1.5 kg of wire

    // 5. The SaaS Base
    val lastUpdated: Long = System.currentTimeMillis(),
    val syncState: SyncStatus = SyncStatus.PENDING_INSERT,
    val isDeleted: Boolean = false // If they stop tracking this product at
)