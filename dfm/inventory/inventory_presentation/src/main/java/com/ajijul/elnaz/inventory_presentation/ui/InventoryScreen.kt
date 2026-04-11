package com.ajijul.elnaz.inventory_presentation.ui

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ajijul.elnaz.core.ui.components.AppProgressOnScreen
import com.ajijul.elnaz.core.ui.components.AppText
import com.ajijul.elnaz.core.ui.components.navBarItemColorCombination
import com.ajijul.elnaz.core.ui.extensions.logoutOrUnauthenticatedNavigation
import com.ajijul.elnaz.core.ui.theme.AppDimens.appNavIconSize
import com.ajijul.elnaz.core.utils.showToast
import com.ajijul.elnaz.di.entrypoints.InventoryDependenciesEntryPoint
import com.ajijul.elnaz.features_manager.gotoDynamicFeature
import com.ajijul.elnaz.features_manager.routes.InventorySubNavHostRoutes
import com.ajijul.elnaz.inventory_presentation.utils.InventoryBottomNavItems
import com.ajijul.elnaz.inventory_presentation.viewmodel.InventoryUiState
import com.ajijul.elnaz.inventory_presentation.viewmodel.InventoryViewModel
import com.ajijul.elnaz.inventory_presentation.viewmodel.InventoryViewModelFactory
import com.ajijul.elnaz.resources.R
import dagger.hilt.android.EntryPointAccessors

@Composable
fun InventoryScreen(
    navController: NavHostController
) {
    val context = LocalContext.current

    // 1. Grab the bridge from the Application Context
    // We use 'remember' so it only fetches this once, not on every UI redraw
    val dependencies = remember(context) {
        EntryPointAccessors.fromApplication(
            context.applicationContext,
            InventoryDependenciesEntryPoint::class.java
        )
    }

    // 2. Create the Factory
    val factory = remember(dependencies) {
        InventoryViewModelFactory(dependencies)
    }

    val viewModel: InventoryViewModel = viewModel(factory = factory)

    val uiState by viewModel.inventoryUiState.collectAsState()

    when (uiState) {
        is InventoryUiState.AuthenticatedUser -> {
            val userModel = (uiState as InventoryUiState.AuthenticatedUser).userModel
            val navTabs = (uiState as InventoryUiState.AuthenticatedUser).navTabs

            // 1. Create the Nested NavHost for the bottom tabs
            val bottomNavController = rememberNavController()

            // 2. Observe the current route to highlight the correct tab automatically
            val navBackStackEntry by bottomNavController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route

            // Find which tab is currently selected based on the NavController's state
            val selectedTab = navTabs.find { it.route == currentRoute } ?: InventoryBottomNavItems.Products
            Scaffold(
                bottomBar = {
                    InventoryNavBottomBar(
                        tabs = navTabs,
                        selectedTab = selectedTab,
                        onTabSelected = { tab ->
                            val destinationExists = bottomNavController.graph.findNode(tab.route) != null
                            if(destinationExists){
                                bottomNavController.navigate(tab.route) {
                                    // This ensures we don't build up an infinite backstack if they click tabs randomly
                                    popUpTo(bottomNavController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            } else {
                                showToast(
                                    context = context,
                                    message = context.getString(R.string.accessDenied)
                                )
                            }
                        }
                    )
                }
            ) { paddingValues ->

                NavHost(
                    navController = bottomNavController,
                    startDestination = InventorySubNavHostRoutes.PRODUCTS.identifier,
                    modifier = Modifier
                        .padding(paddingValues)
                        .background(MaterialTheme.colorScheme.background),
                    enterTransition = { EnterTransition.None },
                    exitTransition = { ExitTransition.None },
                    popEnterTransition = { EnterTransition.None },
                    popExitTransition = { ExitTransition.None }
                ) {

                    val dfmInstaller = viewModel.inventoryDependencies.getDFMInstaller()
                    if (userModel?.canSeeProductsTab == true) {
                        gotoDynamicFeature(
                            navController = bottomNavController,
                            dfmInstaller,
                            moduleNameIdentifierAndDeepLinks = Triple(
                                InventorySubNavHostRoutes.PRODUCTS.moduleName,
                                InventorySubNavHostRoutes.PRODUCTS.identifier,
                                InventorySubNavHostRoutes.PRODUCTS.deepLinks

                            )
                        ) {
                            AppProgressOnScreen()
                        }
                    }
                    if (userModel?.canSeeOrdersTab == true) {
                        gotoDynamicFeature(
                            navController = bottomNavController,
                            dfmInstaller,
                            moduleNameIdentifierAndDeepLinks = Triple(
                                InventorySubNavHostRoutes.ORDERS.moduleName,
                                InventorySubNavHostRoutes.ORDERS.identifier,
                                InventorySubNavHostRoutes.ORDERS.deepLinks

                            )
                        ) {
                            AppProgressOnScreen()
                        }
                    }
                    if (userModel?.canSeeCategoriesTab == true) {
                        gotoDynamicFeature(
                            navController = bottomNavController,
                            dfmInstaller,
                            moduleNameIdentifierAndDeepLinks = Triple(
                                InventorySubNavHostRoutes.CATEGORY.moduleName,
                                InventorySubNavHostRoutes.CATEGORY.identifier,
                                InventorySubNavHostRoutes.CATEGORY.deepLinks

                            )
                        ) {
                            AppProgressOnScreen()
                        }
                    }
                    if (userModel?.canSeeWarehousesTab == true) {
                        gotoDynamicFeature(
                            navController = bottomNavController,
                            dfmInstaller,
                            moduleNameIdentifierAndDeepLinks = Triple(
                                InventorySubNavHostRoutes.WAREHOUSE.moduleName,
                                InventorySubNavHostRoutes.WAREHOUSE.identifier,
                                InventorySubNavHostRoutes.WAREHOUSE.deepLinks

                            )
                        ) {
                            AppProgressOnScreen()
                        }
                    }

                    if (userModel?.canSeeUsersTab == true) {
                        gotoDynamicFeature(
                            navController = bottomNavController,
                            dfmInstaller,
                            moduleNameIdentifierAndDeepLinks = Triple(
                                InventorySubNavHostRoutes.USERS.moduleName,
                                InventorySubNavHostRoutes.USERS.identifier,
                                InventorySubNavHostRoutes.USERS.deepLinks

                            )
                        ) {
                            AppProgressOnScreen()
                        }
                    }

                }
            }

        }

        InventoryUiState.Loading -> AppProgressOnScreen()
        InventoryUiState.UnAuthenticatedUser -> {
            LaunchedEffect(Unit) {
                navController.logoutOrUnauthenticatedNavigation()
            }
        }
    }
}

@Composable
fun InventoryNavBottomBar(
    tabs: List<InventoryBottomNavItems>,
    selectedTab: InventoryBottomNavItems,
    onTabSelected: (InventoryBottomNavItems) -> Unit
) {
    NavigationBar {
        tabs.forEach { tab ->
            val isSelectedTab = selectedTab == tab
            NavigationBarItem(
                selected = isSelectedTab,
                onClick = { onTabSelected.invoke(tab) },
                label = {
                    AppText(
                        text = stringResource(tab.label),
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = if (isSelectedTab) FontWeight.Bold else FontWeight.Medium
                    )
                },
                icon = {
                    Icon(
                        painter = painterResource(tab.icon),
                        contentDescription = stringResource(tab.label),
                        modifier = Modifier.size(appNavIconSize)
                    )
                },
                colors = navBarItemColorCombination()
            )
        }
    }
}