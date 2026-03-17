package com.ajijul.elnaz.feature_category.entry

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.ajijul.elnaz.category_presentation.ui.CategoryScreen
import com.ajijul.elnaz.features_manager.ComposeFeatureModuleEntry
import com.ajijul.elnaz.logger.ElnazLogger
import com.ajijul.elnaz.logger.TAG

class FeatureCategoryEntry : ComposeFeatureModuleEntry {
    @Composable
    override fun DrawEntry(navController: NavHostController) {
        ElnazLogger.i(TAG, "DFM ** CATEGORY ** DRAWING ....")
        CategoryScreen(navController)
    }
}