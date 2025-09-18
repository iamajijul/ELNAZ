package com.ajijul.elnaz.core.utils

sealed class NavGraphRoutes(val route: String) {
    object AUTH : NavGraphRoutes("auth_graph")
    object MAIN : NavGraphRoutes("main_graph")
}