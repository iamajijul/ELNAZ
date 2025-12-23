package com.ajijul.elnaz.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.ajijul.elnaz.core.ui.components.AppProgressOnScreen
import com.ajijul.elnaz.core.ui.extensions.logoutOrUnauthenticatedNavigation
import com.ajijul.elnaz.navigation.InventoryBottomNavItems
import com.ajijul.elnaz.presentation.InventoryViewModel
import com.ajijul.elnaz.utils.InventoryUiState

@Composable
fun InventoryLandingScreen(
    navController: NavHostController
) {
    val viewModel: InventoryViewModel = hiltViewModel()

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

                    when(selectedTab){
                        InventoryBottomNavItems.Category -> TODO()
                        InventoryBottomNavItems.Orders -> TODO()
                        InventoryBottomNavItems.Products -> TODO()
                        InventoryBottomNavItems.Users -> TODO()
                        InventoryBottomNavItems.Warehouse -> TODO()
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
            NavigationBarItem(
                selected = selectedTab == tab,
                onClick = { onTabSelected.invoke(tab) },
                icon = {
                    Icon(
                        painter = painterResource(tab.icon),
                        contentDescription = stringResource(tab.label)
                    )
                }
            )
        }
    }
}