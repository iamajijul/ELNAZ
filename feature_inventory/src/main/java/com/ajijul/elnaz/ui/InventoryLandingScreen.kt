package com.ajijul.elnaz.ui

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.ajijul.elnaz.core.ui.components.AppProgressOnScreen
import com.ajijul.elnaz.navigation.InventoryBottomNavItems
import com.ajijul.elnaz.presentation.InventoryViewModel
import com.ajijul.elnaz.utils.InventoryUiState

@Composable
fun InventoryLandingScreen(
    navGraphBuilder: NavGraphBuilder,
    navController: NavHostController
) {
    val viewModel: InventoryViewModel = hiltViewModel()

    val uiState by viewModel.inventoryUiState.collectAsState()

    var selectedTab by remember { mutableStateOf<InventoryBottomNavItems>(InventoryBottomNavItems.Products) }

    when (uiState) {
        is InventoryUiState.AuthenticatedUser -> {
            val userModel = (uiState as InventoryUiState.AuthenticatedUser).userModel
            val navTabs = (uiState as InventoryUiState.AuthenticatedUser).navTabs

        }

        InventoryUiState.Loading -> AppProgressOnScreen()
        InventoryUiState.UnAuthenticatedUser -> TODO()
    }


}