package com.ajijul.elnaz.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import com.ajijul.elnaz.core.utils.AppNavGraphRoute

fun NavGraphBuilder.inventoryNavGraph(navController: NavHostController) {

    navigation(
        startDestination = InventoryScreen.Products.identifier,
        route = AppNavGraphRoute.INVENTORY.identifier
    ) {

    }
}