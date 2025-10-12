package com.ajijul.elnaz.auth.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.ajijul.elnaz.auth.ui.login.LoginScreen
import com.ajijul.elnaz.auth.ui.splash.SplashScreen
import com.ajijul.elnaz.core.utils.AppNavGraphRoute

fun NavGraphBuilder.authNavGraph(navController: NavHostController) {

    navigation(
        startDestination = AuthScreen.Splash.identifier,
        route = AppNavGraphRoute.AUTH.identifier
    ) {
        composable(AuthScreen.Splash.identifier) {
            SplashScreen(navController)
        }
        composable(AuthScreen.Login.identifier) {
            LoginScreen(navController)
        }
    }

}