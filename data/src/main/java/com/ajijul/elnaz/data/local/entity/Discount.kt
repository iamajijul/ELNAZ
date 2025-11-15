package com.ajijul.elnaz.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.ajijul.elnaz.data.local.entity.utilities.Converters
import com.ajijul.elnaz.domain.model.enums.DiscountStatus
import com.ajijul.elnaz.domain.model.enums.DiscountType

@Entity(
    tableName = "discount",
    indices = [Index(value = ["code"], unique = true)]
)
@TypeConverters(Converters::class)
data class Discount(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

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
    val priority: Int = 0                                    // higher = takes precedence
)


