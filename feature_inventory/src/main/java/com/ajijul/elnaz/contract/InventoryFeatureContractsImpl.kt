package com.ajijul.elnaz.contract

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import com.ajijul.elnaz.core.utils.AppNavGraphRoute
import com.ajijul.elnaz.logger.ElnazLogger
import com.ajijul.elnaz.logger.TAG
import com.ajijul.elnaz.module_contracts.inventory.InventoryFeatureContracts
import com.ajijul.elnaz.navigation.InventoryScreen

class InventoryFeatureContractsImpl : InventoryFeatureContracts {
    override fun registerGraph(builder: Any, navController: Any) {
        if (builder is NavGraphBuilder && navController is NavHostController) {
            builder.navigation(
                startDestination = InventoryScreen.Products.identifier,
                route = AppNavGraphRoute.INVENTORY.identifier
            ) {

            }
        } else {
            ElnazLogger.e(TAG, "Unable to register graph, check parameters")
        }
    }
}