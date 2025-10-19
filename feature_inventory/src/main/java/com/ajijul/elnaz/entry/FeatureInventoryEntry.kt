package com.ajijul.elnaz.entry

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.ajijul.elnaz.features_manager.ComposeFeatureModuleEntry
import com.ajijul.elnaz.features_manager.MainNavGraphRoutes

class FeatureInventoryEntry : ComposeFeatureModuleEntry {

    override fun getNavHostRoute(): String = MainNavGraphRoutes.INVENTORY.identifier
    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController
    ) {
        TODO("Not yet implemented")
    }

}