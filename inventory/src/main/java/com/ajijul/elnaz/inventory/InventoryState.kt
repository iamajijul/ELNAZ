package com.ajijul.elnaz.inventory

import com.ajijul.elnaz.domain.model.Item


data class InventoryState(
    val items: List<Item> = emptyList(),
    val itemCount: Int = 0,
    val totalValue: Double = 0.0,
    val isLoading: Boolean = false,
    val lowStockItems: List<Item> = emptyList(),
    val oldStockItems: List<Item> = emptyList()
)