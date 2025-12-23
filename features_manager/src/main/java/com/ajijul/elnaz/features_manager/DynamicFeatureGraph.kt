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
    moduleNameIdentifierAndDeepLinks: Triple<String, String, List<String>>,
    loadingContent: @Composable () -> Unit
) {
    val (moduleName, route, deepLinks) = moduleNameIdentifierAndDeepLinks
    val deepLinkObjects = deepLinks.map { navDeepLink { uriPattern = it } }

    composable(route = route, deepLinks = deepLinkObjects) {
        var status by remember { mutableStateOf<SplitInstallSessionStatus?>(null) }

        fun doRegisterGraphAndNavigate() {
            val entryPoint = getModuleEntryPoint(moduleName, TAG)
            if (entryPoint != null) {
                entryPoint.registerGraph(navController)
                navController.navigate(entryPoint.getDFMGraphRoute()) {
                    popUpTo(route) {
                        inclusive = true
                    }
                }
            } else {
                ElnazLogger.e(TAG, "DFM $moduleName entry point is null")
            }
        }

        LaunchedEffect(moduleName) {
            if (featureInstaller.isModuleInstalled(moduleName)) {
                ElnazLogger.i(TAG, "DFM $moduleName already installed")
                doRegisterGraphAndNavigate()
            } else {
                ElnazLogger.w(TAG, "DFM $moduleName installing")
                featureInstaller.installModule(moduleName)
                    .collect { installStatus -> status = installStatus }
            }
        }
        status?.let {
            when (status) {
                SplitInstallSessionStatus.INSTALLED -> {
                    LaunchedEffect(Unit) {
                        doRegisterGraphAndNavigate()
                    }
                }
                else -> {
                    loadingContent()
                }
            }
        }
    }
}

private fun getModuleEntryPoint(moduleName: String, tag: String): ComposeFeatureModuleEntry? {
    return try {
        val clazz =
            Class.forName("com.ajijul.elnaz.entry.${moduleName.toPascalCase()}Entry")
        clazz.getDeclaredConstructor().newInstance() as ComposeFeatureModuleEntry
    } catch (e: Exception) {
        ElnazLogger.e(tag, "Exception during REFLECTION " + e.message)
        null
    }
}
