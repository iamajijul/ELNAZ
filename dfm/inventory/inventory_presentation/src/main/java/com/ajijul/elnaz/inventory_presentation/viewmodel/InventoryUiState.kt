package com.ajijul.elnaz.inventory_presentation.viewmodel

import com.ajijul.elnaz.domain.model.UserModel
import com.ajijul.elnaz.inventory_presentation.utils.InventoryBottomNavItems

sealed class InventoryUiState {
    object Loading : InventoryUiState()
    data class AuthenticatedUser(
        val userModel: UserModel?,
        val navTabs: List<InventoryBottomNavItems>
    ) : InventoryUiState()

    object UnAuthenticatedUser : InventoryUiState()
}