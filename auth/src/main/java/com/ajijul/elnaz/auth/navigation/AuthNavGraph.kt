package com.ajijul.elnaz.auth.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.ajijul.elnaz.auth.ui.login.LoginScreen
import com.ajijul.elnaz.auth.ui.splash.SplashScreen
import com.ajijul.elnaz.features_manager.routes.AuthNavGraphRoutes
import com.ajijul.elnaz.features_manager.routes.MainNavGraphRoutes
import com.ajijul.elnaz.features_manager.DynamicFeatureInstaller

fun NavGraphBuilder.navigateToAuthNavGraph(
    navController: NavHostController,
    dynamicFeatureInstaller: DynamicFeatureInstaller
) {

    navigation(
        startDestination = AuthNavGraphRoutes.Splash.identifier,
        route = MainNavGraphRoutes.AUTH.identifier
    ) {
        composable(AuthNavGraphRoutes.Splash.identifier) {
            SplashScreen(navController, dynamicFeatureInstaller)
        }
        composable(AuthNavGraphRoutes.Login.identifier) {
            LoginScreen(navController, dynamicFeatureInstaller)
        }
    }

}