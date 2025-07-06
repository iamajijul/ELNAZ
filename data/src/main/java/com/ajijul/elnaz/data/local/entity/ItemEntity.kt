package com.ajijul.elnaz.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items")
data class ItemEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val barcode: String,
    val quantity: Int,
    val price: Double,
    val lastUpdated: Long,
    val createdAt: Long = System.currentTimeMillis(), // Default to current time
    val location: String = "Unknown" // Default to "Unknown"
)