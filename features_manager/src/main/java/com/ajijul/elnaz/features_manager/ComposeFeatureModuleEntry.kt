package com.ajijul.elnaz.features_manager

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

interface ComposeFeatureModuleEntry {
    @Composable
    fun DrawEntry(navController: NavHostController)
}