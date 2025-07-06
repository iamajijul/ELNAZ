package com.ajijul.elnaz.domain.model

data class Item(
    val id: Int = 0,
    val name: String,
    val barcode: String,
    val quantity: Int,
    val price: Double,
    val lastUpdated: Long,
    val createdAt: Long = System.currentTimeMillis(), // Default to current time if not provided
    val location: String = "Unknown" // Default to "Unknown" if not specified
)
