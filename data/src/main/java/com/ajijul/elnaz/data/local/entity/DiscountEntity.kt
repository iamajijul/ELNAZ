package com.ajijul.elnaz.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.ajijul.elnaz.data.local.entity.utilities.Converters
import com.ajijul.elnaz.domain.model.enums.DiscountStatus
import com.ajijul.elnaz.domain.model.enums.DiscountType
import com.ajijul.elnaz.domain.model.enums.SyncStatus
import java.util.UUID

@Entity(
    tableName = "discount",
    indices = [
        // CRITICAL FIX: Allows Shop A and Shop B to both have a "NEWYEAR20" code!
        Index(value = ["shopId", "code"], unique = true)
    ]
)
@TypeConverters(Converters::class)
data class DiscountEntity(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val shopId: String,

    val code: String,
    val name: String,
    val description: String? = null,

    val discountType: DiscountType = DiscountType.PERCENTAGE, // percentage or fixed amount
    val discountValue: Double,                                // 10% or ₹50
    val maxDiscountAmount: Double? = null,                    // for "up to ₹100"
    val minOrderAmount: Double? = null,                       // optional threshold

    val startDate: Long,
    val endDate: Long?,
    val status: DiscountStatus = DiscountStatus.ACTIVE,
    val priority: Int = 0, // Helps decide which discount applies if multiple are valid

    // The SaaS Base
    val lastUpdated: Long = System.currentTimeMillis(),
    val syncState: SyncStatus = SyncStatus.PENDING_INSERT,
    val isDeleted: Boolean = false // Soft delete for offline sync
)