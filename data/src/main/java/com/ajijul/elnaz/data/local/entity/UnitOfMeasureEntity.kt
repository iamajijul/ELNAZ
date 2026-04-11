package com.ajijul.elnaz.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.ajijul.elnaz.data.local.entity.utilities.Converters
import com.ajijul.elnaz.domain.model.enums.SyncStatus
import java.util.UUID

@Entity(
    tableName = "unit_of_measure",
    indices = [
        // Prevents Shop A from accidentally creating two UOMs named "Kilogram"
        // But allows Shop A and Shop B to both have their own "Kilogram" row
        Index(value = ["shopId", "name"], unique = true),
        Index(value = ["shopId", "symbol"], unique = true)
    ]
)
@TypeConverters(Converters::class)
data class UnitOfMeasureEntity(
    // 1. The Machine ID (UUID)
    @PrimaryKey val id: String = UUID.randomUUID().toString(),

    // 2. The Multi-Tenant Router
    val shopId: String,

    // 3. Core Details
    val name: String,             // e.g., "Meter", "Piece", "Kilogram"
    val symbol: String,           // e.g., "m", "pcs", "kg"
    val isDecimalAllowed: Boolean, // true for meter/weight; false for piece

    // 4. Offline Sync Engine
    val lastUpdated: Long = System.currentTimeMillis(),
    val syncState: SyncStatus = SyncStatus.PENDING_INSERT,
    val isDeleted: Boolean = false // Soft delete for offline sync
)