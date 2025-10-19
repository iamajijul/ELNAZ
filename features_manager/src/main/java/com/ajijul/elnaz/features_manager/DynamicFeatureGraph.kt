package com.ajijul.elnaz.features_manager

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import com.ajijul.elnaz.extension.toPascalCase
import com.ajijul.elnaz.logger.ElnazLogger

fun NavGraphBuilder.dynamicFeature(
    navController: NavHostController,
    featureInstaller: DynamicFeatureInstaller,
    moduleName: String,
    routeAndDeepLinks: Pair<String, List<String>>,
    loadingContent: @Composable () -> Unit
) {
    val moduleDeepLinks = routeAndDeepLinks.second.map { deepLink ->
        navDeepLink {
            uriPattern = deepLink
        }
    }
    composable(
        route = routeAndDeepLinks.first,
        deepLinks = moduleDeepLinks
    ) {
        var isInstalled by remember { mutableStateOf(featureInstaller.isModuleInstalled(moduleName)) }
        val entryPoint = try {
            val clazz =
                Class.forName("com.ajijul.elnaz.$moduleName.${moduleName.toPascalCase()}Entry")
            clazz.getDeclaredConstructor().newInstance() as ComposeFeatureModuleEntry
        } catch (e: Exception) {
            null
        }
        if (isInstalled) {
            // Once registered, navigate to the newly available graph
            LaunchedEffect(entryPoint) {
                entryPoint?.let {
                    navController.navigate(it.getNavHostRoute()) {
                        // Pop the intermediate deep link destination
                        popUpTo(routeAndDeepLinks.first) { inclusive = true }
                    }
                }
            }
        } else {
            val context = LocalContext.current
            LaunchedEffect(moduleName) {
                featureInstaller.installModule(moduleName) {
                    try {
                        entryPoint?.registerGraph(this@dynamicFeature, navController)
                        isInstalled = true
                    } catch (e: Exception) {
                        ElnazLogger.e("DynamicFeatureNav", "Failed to register graph: ${e.message}")
                    }
                }
            }
        }
    }
}