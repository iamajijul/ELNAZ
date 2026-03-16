package com.ajijul.elnaz.auth.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.ajijul.elnaz.auth.ui.login.LoginScreen
import com.ajijul.elnaz.auth.ui.splash.SplashScreen
import com.ajijul.elnaz.features_manager.routes.AuthSubNavGraphRoutes
import com.ajijul.elnaz.features_manager.routes.MainNavGraphRoutes

fun NavGraphBuilder.navigateToAuthNavGraph(
    navController: NavHostController
) {

    navigation(
        route = MainNavGraphRoutes.AUTH.identifier,
        startDestination = AuthSubNavGraphRoutes.Splash.identifier
    ) {
        composable(AuthSubNavGraphRoutes.Splash.identifier) {
            SplashScreen(navController)
        }
        composable(AuthSubNavGraphRoutes.Login.identifier) {
            LoginScreen(navController)
        }
    }

}