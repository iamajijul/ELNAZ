package com.ajijul.elnaz.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.ajijul.elnaz.auth.navigation.authNavGraph
import com.ajijul.elnaz.core.utils.AppNavGraphRoute
import com.ajijul.elnaz.core.contract.DynamicFeatureModuleInstaller

@Composable
fun AppNavGraph(dynamicFeatureModuleInstaller: DynamicFeatureModuleInstaller) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AppNavGraphRoute.AUTH.identifier
    ) {
        authNavGraph(navController)
    }
}