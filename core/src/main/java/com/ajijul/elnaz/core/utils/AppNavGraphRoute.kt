package com.ajijul.elnaz.core.utils

sealed class AppNavGraphRoute(val identifier: String) {
    object AUTH : AppNavGraphRoute("auth_graph")
    object INVENTORY : AppNavGraphRoute("inventory_graph")
}