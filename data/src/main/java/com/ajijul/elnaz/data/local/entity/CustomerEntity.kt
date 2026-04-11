package com.ajijul.elnaz.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.ajijul.elnaz.data.local.entity.utilities.Converters
import com.ajijul.elnaz.domain.model.enums.BalanceType
import com.ajijul.elnaz.domain.model.enums.SyncStatus
import java.util.UUID

@Entity(
    tableName = "customer",
    indices = [
        // CRITICAL: Prevent creating two customers with the same phone number in the same shop
        Index(value = ["shopId", "phone"], unique = true)
    ]
)
@TypeConverters(Converters::class)
data class CustomerEntity(
    // 1. Machine ID
    @PrimaryKey val id: String = UUID.randomUUID().toString(), // (autoGenerate = false is default for Strings, so we can omit it)

    // 2. SaaS Router
    val shopId: String,

    // 3. Core Details
    val name: String,
    val phone: String, // Usually the true unique identifier for a retail customer
    val email: String?, // Made nullable for fast checkout
    val address: String?, // Made nullable for fast checkout
    val customerSince: Long = System.currentTimeMillis(),

    // 4. Khata / Ledger
    val openingBalance: Double = 0.0,
    val balanceType: BalanceType = BalanceType.RECEIVABLE,

    // 5. The SaaS Sync Base
    val lastUpdated: Long = System.currentTimeMillis(),
    val syncState: SyncStatus = SyncStatus.PENDING_INSERT,
    val isDeleted: Boolean = false
)