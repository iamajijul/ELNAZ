package com.ajijul.elnaz.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.ajijul.elnaz.auth.navigation.authNavGraph
import com.ajijul.elnaz.features_manager.MainNavGraphRoutes
import com.ajijul.elnaz.features_manager.DynamicFeatureInstaller

@Composable
fun AppNavGraph(dynamicFeatureInstaller: DynamicFeatureInstaller) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = MainNavGraphRoutes.AUTH.identifier
    ) {
        authNavGraph(navController, dynamicFeatureInstaller)

    }
}