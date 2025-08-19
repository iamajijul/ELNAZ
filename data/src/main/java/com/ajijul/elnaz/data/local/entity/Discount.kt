package com.ajijul.elnaz.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "discount")
data class Discount(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val name: String,
    val discountType: String, // "PERCENTAGE" or "FLAT"
    val value: Double,
    val startDate: Long?,
    val endDate: Long?,
    val isActive: Boolean = true,
    val priority: Int = 0      // higher = takes precedence
)

