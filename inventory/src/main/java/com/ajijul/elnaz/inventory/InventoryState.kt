package com.ajijul.elnaz.inventory

import com.ajijul.elnaz.domain.model.ProductModel


data class InventoryState(
    val products: List<ProductModel> = emptyList(),
    val itemCount: Int = 0,
    val totalValue: Double = 0.0,
    val isLoading: Boolean = false,
    val lowStockProducts: List<ProductModel> = emptyList(),
    val oldStockProducts: List<ProductModel> = emptyList()
)