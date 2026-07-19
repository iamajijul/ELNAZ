package com.ajijul.elnaz.feature_inventory.entry

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.ajijul.elnaz.features_manager.ComposeFeatureModuleEntry
import com.ajijul.elnaz.inventory_presentation.ui.InventoryScreen
import com.ajijul.elnaz.logger.ElnazLogger
import com.ajijul.elnaz.logger.TAG

class FeatureInventoryEntry : ComposeFeatureModuleEntry {
    @Composable
    override fun DrawEntry(
        navController: NavHostController
    ) {
        ElnazLogger.i(TAG, "DFM ** INVENTORY ** DRAWING ....")
        InventoryScreen(navController)
    }
}