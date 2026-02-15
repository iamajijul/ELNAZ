package com.ajijul.elnaz.inventory_presentation.utils

import com.ajijul.elnaz.domain.model.UserModel

sealed class InventoryUiState {
    object Loading : InventoryUiState()
    data class AuthenticatedUser(
        val userModel: UserModel?,
        val navTabs: List<InventoryBottomNavItems>
    ) : InventoryUiState()

    object UnAuthenticatedUser : InventoryUiState()
}