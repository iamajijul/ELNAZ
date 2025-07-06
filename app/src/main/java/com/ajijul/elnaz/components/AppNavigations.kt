package com.ajijul.elnaz.components

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ajijul.elnaz.core.common.NavRoutes
import com.ajijul.elnaz.inventory.InventoryScreen
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallRequest

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val splitInstallManager = SplitInstallManagerFactory.create(navController.context)

    NavHost(navController = navController, startDestination = NavRoutes.INVENTORY){

        composable(NavRoutes.INVENTORY){
            InventoryScreen(
                onNavigateToAddItem = {
                    val request = SplitInstallRequest.newBuilder()
                        .addModule(NavRoutes.FEATURE_ADD_ITEM)
                        .build()
                    splitInstallManager.startInstall(request)
                        .addOnSuccessListener { navController.navigate(NavRoutes.ADD_ITEM) }
                        .addOnFailureListener { /* Handle failure */ }
                },
                onNavigateToBarcodeScanner = {
                    val request = SplitInstallRequest.newBuilder()
                        .addModule(NavRoutes.FEATURE_BARCODE)
                        .build()
                    splitInstallManager.startInstall(request)
                        .addOnSuccessListener { navController.navigate(NavRoutes.BARCODE_SCANNER) }
                        .addOnFailureListener { /* Handle failure */ }
                }
            )
            composable(NavRoutes.ADD_ITEM) {
                // Dynamically loaded by :additem
            }
            composable(NavRoutes.BARCODE_SCANNER) {
                // Dynamically loaded by :barcode
            }
        }
    }
}