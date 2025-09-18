package com.ajijul.elnaz.auth.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.ajijul.elnaz.core.utils.NavGraphRoutes

fun NavGraphBuilder.authNavGraph() {

    navigation(
        startDestination = AuthScreen.Splash.route,
        route = NavGraphRoutes.AUTH.route
    ) {

    }

}