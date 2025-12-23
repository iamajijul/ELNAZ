package com.ajijul.elnaz.core.ui.extensions

import androidx.navigation.NavHostController
import com.ajijul.elnaz.features_manager.routes.AuthNavGraphRoutes
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.compose.ComposeNavigator

fun NavHostController.logoutOrUnauthenticatedNavigation() {
    navigate(AuthNavGraphRoutes.Login.identifier) {
        popUpTo(graph.startDestinationId) {
            inclusive = true
        }
        launchSingleTop = true
    }
}

/**
 * Extension to dynamically inject a Composable destination into the running NavGraph.
 * This effectively replaces "composable(route) { }" for lazy-loaded modules.
 */
fun NavHostController.registerDynamicScreen(
    route: String,
    content: @Composable () -> Unit
) {
    // 1. Check if it already exists to prevent crashes on re-entry
    if (graph.findNode(route) != null) {
        return
    }

    // 2. Get the specific Compose Navigator
    val navigator = navigatorProvider.getNavigator(ComposeNavigator::class.java)

    // 3. create the destination manually
    val destination = ComposeNavigator.Destination(navigator) {
        content.invoke()
    }
    destination.route = route

    // 4. Add it to the graph
    graph.addDestination(destination)
}