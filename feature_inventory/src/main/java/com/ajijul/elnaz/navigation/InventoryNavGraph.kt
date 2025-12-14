package com.ajijul.elnaz.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import com.ajijul.elnaz.features_manager.MainNavGraphRoutes

fun NavGraphBuilder.inventoryNavGraph(navController: NavHostController) {

    navigation(
        startDestination = AllInventoryScreenTypes.Products.identifier,
        route = MainNavGraphRoutes.INVENTORY.identifier
    ) {

    }
}