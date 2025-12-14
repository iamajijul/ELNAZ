package com.ajijul.elnaz.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.ajijul.elnaz.auth.navigation.navigateToAuthNavGraph
import com.ajijul.elnaz.core.ui.components.AppProgressOnScreen
import com.ajijul.elnaz.features_manager.DynamicFeatureInstaller
import com.ajijul.elnaz.features_manager.MainNavGraphRoutes
import com.ajijul.elnaz.features_manager.gotoDynamicFeature

@Composable
fun MainNavGraph(dynamicFeatureInstaller: DynamicFeatureInstaller) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = MainNavGraphRoutes.AUTH.identifier
    ) {
        navigateToAuthNavGraph(navController, dynamicFeatureInstaller)
        gotoDynamicFeature(
            navController = navController,
            featureInstaller = dynamicFeatureInstaller,
            moduleNameIdentifierAndDeepLinks = Triple(
                MainNavGraphRoutes.INVENTORY.moduleName,
                MainNavGraphRoutes.INVENTORY.identifier,
                MainNavGraphRoutes.INVENTORY.deepLinks
            )
        ) {
            AppProgressOnScreen()
        }
    }
}