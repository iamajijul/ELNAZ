package com.ajijul.elnaz.entry

import androidx.navigation.NavHostController
import com.ajijul.elnaz.core.ui.extensions.registerDynamicScreen
import com.ajijul.elnaz.features_manager.ComposeFeatureModuleEntry
import com.ajijul.elnaz.features_manager.routes.MainNavGraphRoutes
import com.ajijul.elnaz.logger.ElnazLogger
import com.ajijul.elnaz.logger.TAG
import com.ajijul.elnaz.ui.InventoryLandingScreen

class FeatureInventoryEntry : ComposeFeatureModuleEntry {

    override fun getDFMGraphRoute(): String =
        MainNavGraphRoutes.INVENTORY.moduleName + "_" + MainNavGraphRoutes.INVENTORY.identifier

    override fun registerGraph(
        navController: NavHostController
    ) {
        ElnazLogger.i(TAG, "DFM INVENTORY INSTALLED ....")
        navController.registerDynamicScreen(getDFMGraphRoute()) {
            InventoryLandingScreen(navController)
        }
    }

}