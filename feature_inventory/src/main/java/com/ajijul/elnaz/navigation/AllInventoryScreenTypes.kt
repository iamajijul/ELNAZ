package com.ajijul.elnaz.navigation

sealed class AllInventoryScreenTypes(var identifier: String) {
    object Products : AllInventoryScreenTypes("products")
    object Orders : AllInventoryScreenTypes("orders")
    object Warehouses : AllInventoryScreenTypes("warehouses")
    object Categories : AllInventoryScreenTypes("categories")
    object Users : AllInventoryScreenTypes("users")
}