package com.ajijul.elnaz.entry

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.ajijul.elnaz.di.annotations.IODispatcher
import com.ajijul.elnaz.domain.repository.user.UserPreferenceRepository
import com.ajijul.elnaz.domain.usecases.auth.CurrentUserUseCase
import com.ajijul.elnaz.features_manager.ComposeFeatureModuleEntry
import com.ajijul.elnaz.features_manager.MainNavGraphRoutes
import com.ajijul.elnaz.logger.ElnazLogger
import com.ajijul.elnaz.logger.TAG
import com.ajijul.elnaz.navigation.inventoryNavGraph
import com.ajijul.elnaz.ui.InventoryLandingScreen
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class FeatureInventoryEntry : ComposeFeatureModuleEntry {

    override fun getNavHostRoute(): String = MainNavGraphRoutes.INVENTORY.identifier
    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController
    ) {
        ElnazLogger.i(TAG, "DFM INVENTORY INSTALLED ....")
        navGraphBuilder.composable("i"){
            InventoryLandingScreen(navGraphBuilder, navController)
        }
    }

}