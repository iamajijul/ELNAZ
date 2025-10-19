package com.ajijul.elnaz.features_manager

sealed class MainNavGraphRoutes(
    val identifier: String,
    val moduleName: String,
    val deepLinks: List<String>
) {
    object AUTH : MainNavGraphRoutes("auth_graph", "auth", emptyList())

    object INVENTORY : MainNavGraphRoutes("inventory_graph", "feature_inventory", emptyList())
}