package com.ajijul.elnaz.navigation

sealed class InventoryScreen(var identifier: String) {
    object Products : InventoryScreen("products")
    object Orders : InventoryScreen("orders")
    object Warehouses : InventoryScreen("warehouses")
    object Categories : InventoryScreen("categories")
    object Users : InventoryScreen("users")
}