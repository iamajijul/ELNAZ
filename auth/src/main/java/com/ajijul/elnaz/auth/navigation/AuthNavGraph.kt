package com.ajijul.elnaz.auth.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.ajijul.elnaz.auth.ui.splash.SplashScreen
import com.ajijul.elnaz.core.utils.NavGraphRoutes

fun NavGraphBuilder.authNavGraph(navController: NavHostController) {

    navigation(
        startDestination = AuthScreen.Splash.route,
        route = NavGraphRoutes.AUTH.route
    ) {
        composable(AuthScreen.Splash.route) {
            SplashScreen(navController)
        }
    }

}