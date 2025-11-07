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
        var didRegister by remember { mutableStateOf(false) }

        LaunchedEffect(moduleName) {
            if (featureInstaller.isModuleInstalled(moduleName)) {
                ElnazLogger.i(TAG, "DFM $moduleName already installed")
                status = SplitInstallSessionStatus.INSTALLED
            } else {
                ElnazLogger.w(TAG, "DFM $moduleName installing")
                featureInstaller.installModule(moduleName)
                    .collect { installStatus -> status = installStatus }
            }
        }

        // Just register, don't navigate!
        LaunchedEffect(status) {
            ElnazLogger.i(TAG, "DFM Status of installing $moduleName is $status")
            if (status == SplitInstallSessionStatus.INSTALLED && !didRegister) {
                didRegister = true
                ElnazLogger.i(TAG, "DFM $moduleName registering graph")
                val entryPoint = getModuleEntryPoint(moduleName, TAG)
                if (entryPoint != null) {
                    entryPoint.registerGraph(this@gotoDynamicFeature, navController)
                    // NO navigation here! The route is already active
                } else {
                    ElnazLogger.e(TAG, "DFM $moduleName entry point is null")
                }
            }
        }

        when (status) {
            SplitInstallSessionStatus.DOWNLOADING,
            SplitInstallSessionStatus.INSTALLING,
            SplitInstallSessionStatus.PENDING -> {
                loadingContent()
            }

            SplitInstallSessionStatus.REQUIRES_USER_CONFIRMATION -> {
//                Column(
//                    modifier = Modifier.fillMaxSize(),
//                    horizontalAlignment = Alignment.CenterHorizontally,
//                    verticalArrangement = Arrangement.Center
//                ) {
//                    Text("Please confirm installation in Google Play")
//                }
            }

            SplitInstallSessionStatus.FAILED -> {
//                Column(
//                    modifier = Modifier.fillMaxSize(),
//                    horizontalAlignment = Alignment.CenterHorizontally,
//                    verticalArrangement = Arrangement.Center
//                ) {
//                    Text("Installation failed")
//                    Spacer(modifier = Modifier.height(16.dp))
//                    Button(onClick = {
//                        status = null
//                        didRegister = false
//                    }) {
//                        Text("Retry")
//                    }
//                }
            }

            else -> {
                loadingContent()
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
