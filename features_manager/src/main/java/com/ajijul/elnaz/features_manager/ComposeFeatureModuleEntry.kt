package com.ajijul.elnaz.features_manager

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

interface ComposeFeatureModuleEntry {
    fun getNavHostRoute(): String
    fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController
    )
}