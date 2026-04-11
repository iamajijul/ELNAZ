package com.ajijul.elnaz.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.ajijul.elnaz.data.local.entity.utilities.Converters
import com.ajijul.elnaz.domain.model.enums.BalanceType
import com.ajijul.elnaz.domain.model.enums.SupplierStatus
import com.ajijul.elnaz.domain.model.enums.SyncStatus
import java.util.UUID

@Entity(
    tableName = "supplier",
    indices = [
        // CRITICAL FIX: Ensures supplier names and codes are only unique WITHIN a specific shop.
        // If two different shops both have a supplier named "ABC Electronics", the database won't crash.
        Index(value = ["shopId", "name"], unique = true),
        Index(value = ["shopId", "supplierCode"], unique = true)
    ]
)
@TypeConverters(Converters::class)
data class SupplierEntity(
    // 1. The Machine ID (UUID instead of Auto-Generate Long)
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val shopId: String, // The Multi-Tenant router

    // 2. Core Supplier Details
    val supplierCode: String,
    val name: String,
    val email: String?,
    val phone: String?,
    val address: String?,
    val gstNumber: String?,
    val supplierLogo: String? = null, // Added for UI aesthetics
    val status: SupplierStatus = SupplierStatus.ACTIVE,

    // 3. Khata (Ledger) Tracking
    val openingBalance: Double = 0.0,
    val balanceType: BalanceType = BalanceType.PAYABLE,

    // 4. Offline Sync Engine
    val createdAt: Long = System.currentTimeMillis(),
    val lastUpdated: Long = System.currentTimeMillis(),
    val syncState: SyncStatus = SyncStatus.PENDING_INSERT,
    val isDeleted: Boolean = false // Soft delete for offline sync
)