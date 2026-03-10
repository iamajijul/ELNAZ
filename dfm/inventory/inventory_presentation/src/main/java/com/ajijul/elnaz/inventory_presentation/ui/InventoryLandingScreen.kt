package com.ajijul.elnaz.inventory_presentation.ui

import androidx.compose.foundation.layout.Box
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.ajijul.elnaz.core.ui.components.AppProgressOnScreen
import com.ajijul.elnaz.core.ui.components.AppText
import com.ajijul.elnaz.core.ui.extensions.logoutOrUnauthenticatedNavigation
import com.ajijul.elnaz.core.utils.AppDimens.appNavIconSize
import com.ajijul.elnaz.di.entrypoints.InventoryDependenciesEntryPoint
import com.ajijul.elnaz.inventory_presentation.utils.InventoryBottomNavItems
import com.ajijul.elnaz.inventory_presentation.viewmodel.InventoryViewModel
import com.ajijul.elnaz.inventory_presentation.utils.InventoryUiState
import com.ajijul.elnaz.inventory_presentation.viewmodel.InventoryViewModelFactory
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.android.EntryPointAccessors.fromApplication

@Composable
fun InventoryLandingScreen(
    navController: NavHostController
) {
    val context = LocalContext.current

    // 1. Grab the bridge from the Application Context
    // We use 'remember' so it only fetches this once, not on every UI redraw
    val dependencies = remember(context) {
        fromApplication(
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

    var selectedTab by remember { mutableStateOf<InventoryBottomNavItems>(InventoryBottomNavItems.Products) }

    when (uiState) {
        is InventoryUiState.AuthenticatedUser -> {
            val userModel = (uiState as InventoryUiState.AuthenticatedUser).userModel
            val navTabs = (uiState as InventoryUiState.AuthenticatedUser).navTabs
            Scaffold(
                bottomBar = {
                    InventoryNavBottomBar(
                        tabs = navTabs,
                        selectedTab = selectedTab,
                        onTabSelected = { selectedTab = it }
                    )
                }
            ) { paddingValues ->
                Box(Modifier.padding(paddingValues)) {

                    when (selectedTab) {
                        InventoryBottomNavItems.Category -> {}
                        InventoryBottomNavItems.Orders -> {}
                        InventoryBottomNavItems.Products -> {}
                        InventoryBottomNavItems.Users -> {}
                        InventoryBottomNavItems.Warehouse -> {}
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
                colors = NavigationBarItemDefaults.colors(
                    // SELECTED STATE
                    // The "Pill" background color (Your Brand Pink)
                    indicatorColor = MaterialTheme.colorScheme.primary,
                    // The Icon color inside the pill (White/Inverse)
                    selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                    // The Text color when selected
                    selectedTextColor = MaterialTheme.colorScheme.primary,

                    // UNSELECTED STATE
                    // Icon color when not selected (Greyish)
                    unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    // Text color when not selected
                    unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
                )
            )
        }
    }
}