package com.ajijul.elnaz.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.ajijul.elnaz.auth.navigation.authNavGraph
import com.ajijul.elnaz.core.ui.components.AppProgress
import com.ajijul.elnaz.features_manager.MainNavGraphRoutes
import com.ajijul.elnaz.features_manager.DynamicFeatureInstaller
import com.ajijul.elnaz.features_manager.gotoDynamicFeature

@Composable
fun AppNavGraph(dynamicFeatureInstaller: DynamicFeatureInstaller) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = MainNavGraphRoutes.AUTH.identifier
    ) {
        authNavGraph(navController, dynamicFeatureInstaller)
        gotoDynamicFeature(
            navController = navController,
            featureInstaller = dynamicFeatureInstaller,
            moduleName = MainNavGraphRoutes.INVENTORY.moduleName,
            routeAndDeepLinks = Pair(
                MainNavGraphRoutes.INVENTORY.identifier,
                MainNavGraphRoutes.INVENTORY.deepLinks
            )
        ) {
            AppProgress(modifier = Modifier.fillMaxSize())
        }
    }
}