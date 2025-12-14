package com.ajijul.elnaz.utils

import com.ajijul.elnaz.domain.model.UserModel
import com.ajijul.elnaz.navigation.InventoryBottomNavItems

sealed class InventoryUiState {
    object Loading : InventoryUiState()
    data class AuthenticatedUser(
        val userModel: UserModel?,
        val navTabs: List<InventoryBottomNavItems>
    ) : InventoryUiState()

    object UnAuthenticatedUser : InventoryUiState()
}