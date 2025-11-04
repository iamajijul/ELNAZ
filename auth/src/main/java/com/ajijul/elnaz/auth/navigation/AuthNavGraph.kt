package com.ajijul.elnaz.auth.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.ajijul.elnaz.auth.ui.login.LoginScreen
import com.ajijul.elnaz.auth.ui.splash.SplashScreen
import com.ajijul.elnaz.features_manager.MainNavGraphRoutes
import com.ajijul.elnaz.features_manager.DynamicFeatureInstaller

fun NavGraphBuilder.navigateToAuthNavGraph(
    navController: NavHostController,
    dynamicFeatureInstaller: DynamicFeatureInstaller
) {

    navigation(
        startDestination = AuthScreen.Splash.identifier,
        route = MainNavGraphRoutes.AUTH.identifier
    ) {
        composable(AuthScreen.Splash.identifier) {
            SplashScreen(navController, dynamicFeatureInstaller)
        }
        composable(AuthScreen.Login.identifier) {
            LoginScreen(navController, dynamicFeatureInstaller)
        }
    }

}