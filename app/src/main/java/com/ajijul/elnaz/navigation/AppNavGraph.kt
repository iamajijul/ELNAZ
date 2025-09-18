package com.ajijul.elnaz.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.ajijul.elnaz.auth.navigation.authNavGraph
import com.ajijul.elnaz.core.utils.NavGraphRoutes
import com.ajijul.elnaz.startup.FeatureModuleInstaller

@Composable
fun AppNavGraph(featureModuleInstaller: FeatureModuleInstaller) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = NavGraphRoutes.AUTH.route
    ) {
        authNavGraph()
    }
}