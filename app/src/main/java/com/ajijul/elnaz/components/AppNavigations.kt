package com.ajijul.elnaz.components

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ajijul.elnaz.core.utils.NavRoutes
import com.ajijul.elnaz.startup.FeatureModuleInstaller

@Composable
fun AppNavigation(featureModuleInstaller: FeatureModuleInstaller) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = NavRoutes.INVENTORY) {

        /*composable(NavRoutes.INVENTORY) {
            InventoryScreen(
                onNavigateToAddItem = {
                    featureModuleInstaller.installModule(NavRoutes.FEATURE_PRODUCT_MANAGEMENT) {
                        navController.navigate(NavRoutes.PRODUCT_MANAGEMENT)
                    }
                },
                onNavigateToBarcodeScanner = {
                    featureModuleInstaller.installModule(NavRoutes.FEATURE_BARCODE) {
                        navController.navigate(NavRoutes.BARCODE_SCANNER)
                    }
                }
            )
        }

        composable(NavRoutes.PRODUCT_MANAGEMENT) {
            // Dynamically loaded by :additem
        }

        composable(NavRoutes.BARCODE_SCANNER) {
            // Dynamically loaded by :barcode
        }*/

    }
}