package com.ajijul.elnaz.features_manager

sealed class MainNavGraphRoutes(val identifier: String) {
    object AUTH : MainNavGraphRoutes("auth_graph")
    object INVENTORY : MainNavGraphRoutes("inventory_graph")
}