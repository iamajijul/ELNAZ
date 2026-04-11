package com.ajijul.elnaz.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.ajijul.elnaz.data.local.entity.utilities.Converters
import com.ajijul.elnaz.domain.model.enums.SyncStatus
import com.ajijul.elnaz.domain.model.enums.WarehouseStatus
import java.util.UUID

@Entity(
    tableName = "warehouse",
    indices = [
        // CRITICAL FIX: Ensures warehouse names and codes are unique ONLY within a specific shop.
        Index(value = ["shopId", "name"], unique = true),
        Index(value = ["shopId", "code"], unique = true)
    ]
)
@TypeConverters(Converters::class)
data class WarehouseEntity(
    // 1. The Machine ID (UUID)
    @PrimaryKey val id: String = UUID.randomUUID().toString(),

    // 2. The Multi-Tenant Router
    val shopId: String,

    // 3. Core Warehouse Details
    val code: String, // Human-readable identifier, e.g., "DELHI01"
    val name: String,
    val location: String,
    val contactNumber: String?,
    val managerName: String?,
    val status: WarehouseStatus = WarehouseStatus.ACTIVE,

    // 4. Offline Sync Engine
    val createdAt: Long = System.currentTimeMillis(),
    val lastUpdated: Long = System.currentTimeMillis(),
    val syncState: SyncStatus = SyncStatus.PENDING_INSERT,
    val isDeleted: Boolean = false // Soft delete for offline sync
)