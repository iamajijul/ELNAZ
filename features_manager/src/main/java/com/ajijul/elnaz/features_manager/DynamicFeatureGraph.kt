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
        //1. Check if it is installed BEFORE the first frame is drawn!
        var entryPoint by remember(moduleName) {
            mutableStateOf(
                if (featureInstaller.isModuleInstalled(moduleName)) {
                    // If it's already on the phone, load it instantly. No null state!
                    DfmEntryPointCache.get(moduleName, TAG)
                } else {
                    null
                }
            )
        }
        // 2. Fetch the DFM or trigger the download
        LaunchedEffect(moduleName) {
            if (entryPoint == null) {
                ElnazLogger.w(TAG, "DFM $moduleName installing")
                featureInstaller.installModule(moduleName)
                    .collect { installStatus ->
                        if (installStatus == SplitInstallSessionStatus.INSTALLED) {
                            ElnazLogger.v(TAG, "DFM $moduleName install complete")
                            entryPoint = DfmEntryPointCache.get(moduleName, TAG)
                        }
                    }
            }
        }

        // 3. The "Drawing" DFM UI
        if (entryPoint != null) {
            ElnazLogger.v(TAG, "DFM Entry Point for $moduleName is NOT NULL")
            entryPoint?.DrawEntry(navController)
        } else {
            ElnazLogger.v(TAG, "DFM Entry Point for $moduleName is NULL")
            loadingContent()
        }
    }
}
