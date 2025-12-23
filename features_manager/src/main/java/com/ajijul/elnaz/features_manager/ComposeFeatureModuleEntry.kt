package com.ajijul.elnaz.features_manager

import androidx.navigation.NavHostController

interface ComposeFeatureModuleEntry {
    fun getDFMGraphRoute(): String
    fun registerGraph(navController: NavHostController)
}