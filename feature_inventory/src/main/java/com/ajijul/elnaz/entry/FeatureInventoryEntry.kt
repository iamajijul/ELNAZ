package com.ajijul.elnaz.entry

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.ajijul.elnaz.features_manager.ComposeFeatureModuleEntry
import com.ajijul.elnaz.features_manager.MainNavGraphRoutes
import com.ajijul.elnaz.logger.ElnazLogger
import com.ajijul.elnaz.logger.TAG
import com.ajijul.elnaz.navigation.inventoryNavGraph

class FeatureInventoryEntry : ComposeFeatureModuleEntry {

    override fun getNavHostRoute(): String = MainNavGraphRoutes.INVENTORY.identifier
    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController
    ) {
        ElnazLogger.i(TAG, "DFM INVENTORY INSTALLED ....")
        navGraphBuilder.inventoryNavGraph(navController)
    }

}