package com.ajijul.elnaz.feature_category.entry

import androidx.navigation.NavHostController
import com.ajijul.elnaz.category_presentation.ui.CategoryScreen
import com.ajijul.elnaz.core.ui.extensions.registerDynamicScreen
import com.ajijul.elnaz.features_manager.ComposeFeatureModuleEntry
import com.ajijul.elnaz.features_manager.routes.InventorySubNavHostRoutes
import com.ajijul.elnaz.logger.ElnazLogger
import com.ajijul.elnaz.logger.TAG

class FeatureCategoryEntry : ComposeFeatureModuleEntry {
    override fun getDFMGraphRoute(): String =
        InventorySubNavHostRoutes.CATEGORY.moduleName + "_" + InventorySubNavHostRoutes.CATEGORY.identifier

    override fun registerGraph(navController: NavHostController) {
        ElnazLogger.i(TAG, "DFM ** CATEGORY ** INSTALLED ....")
        navController.registerDynamicScreen(getDFMGraphRoute())  {
            CategoryScreen(navController)
        }
    }
}