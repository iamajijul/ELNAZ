package com.ajijul.elnaz.components

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ajijul.elnaz.core.utils.NavRoutes
import com.ajijul.elnaz.extension.TAG
import com.ajijul.elnaz.inventory.InventoryScreen
import com.ajijul.elnaz.logger.ElnazLogger
import com.ajijul.elnaz.startup.FeatureModuleInstaller
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallRequest

@Composable
fun AppNavigation(featureModuleInstaller: FeatureModuleInstaller) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = NavRoutes.INVENTORY) {

        composable(NavRoutes.INVENTORY) {
            InventoryScreen(
                onNavigateToAddItem = {
                    featureModuleInstaller.installModule(NavRoutes.FEATURE_ADD_ITEM) {
                        navController.navigate(NavRoutes.ADD_ITEM)
                    }
                },
                onNavigateToBarcodeScanner = {
                    featureModuleInstaller.installModule(NavRoutes.FEATURE_BARCODE) {
                        navController.navigate(NavRoutes.BARCODE_SCANNER)
                    }
                }
            )
        }

        composable(NavRoutes.ADD_ITEM) {
            // Dynamically loaded by :additem
        }

        composable(NavRoutes.BARCODE_SCANNER) {
            // Dynamically loaded by :barcode
        }

    }
}