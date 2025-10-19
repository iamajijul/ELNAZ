package com.ajijul.elnaz.features_manager

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import com.ajijul.elnaz.extension.toPascalCase
import com.ajijul.elnaz.logger.ElnazLogger
import com.ajijul.elnaz.logger.TAG

fun NavGraphBuilder.gotoDynamicFeature(
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

        LaunchedEffect(moduleName) {
            if (isInstalled) {
                val entryPoint = try {
                    val clazz =
                        Class.forName("com.ajijul.elnaz.$moduleName.${moduleName.toPascalCase()}Entry")
                    clazz.getDeclaredConstructor().newInstance() as ComposeFeatureModuleEntry
                } catch (e: Exception) {
                    ElnazLogger.e(TAG, "Exception during REFLECTION " + e.message)
                    null
                }
                ElnazLogger.e(TAG, "Dynamic feature already INSTALLED $moduleName")
                entryPoint?.let {
                    navController.navigate(it.getNavHostRoute()) {
                        popUpTo(routeAndDeepLinks.first) { inclusive = true }
                    }
                }
            } else {
                ElnazLogger.e(
                    TAG,
                    "Dynamic feature NOT INSTALLED $moduleName, starting installation"
                )
                featureInstaller.installModule(moduleName) {
                    try {
                        val entryPoint = try {
                            val clazz =
                                Class.forName("com.ajijul.elnaz.$moduleName.${moduleName.toPascalCase()}Entry")
                            clazz.getDeclaredConstructor().newInstance() as ComposeFeatureModuleEntry
                        } catch (e: Exception) {
                            ElnazLogger.e(TAG, "Exception during REFLECTION " + e.message)
                            null
                        }
                        entryPoint?.registerGraph(this@gotoDynamicFeature, navController)
                        isInstalled = true
                    } catch (e: Exception) {
                        ElnazLogger.e("DynamicFeatureNav", "Failed to register graph: ${e.message}")
                    }
                }
            }
        }
        if (!isInstalled) {
            loadingContent()
        }
    }
}