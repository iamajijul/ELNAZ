package com.ajijul.elnaz.inventory_presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ajijul.elnaz.domain.model.UserModel
import com.ajijul.elnaz.domain.model.enums.Resource
import com.ajijul.elnaz.domain.model.enums.UserRole
import com.ajijul.elnaz.domain.usecases.auth.CurrentUserUseCase
import com.ajijul.elnaz.inventory_presentation.utils.InventoryBottomNavItems
import com.ajijul.elnaz.inventory_presentation.utils.InventoryUiState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class InventoryViewModel(
    private val currentUserUseCase: CurrentUserUseCase,
    ioDispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _inventoryUiState = MutableStateFlow<InventoryUiState>(InventoryUiState.Loading)
    val inventoryUiState: StateFlow<InventoryUiState> = _inventoryUiState

    init {
        viewModelScope.launch(ioDispatcher) {
            when (val currentUserUseCase: Resource<UserModel?> = currentUserUseCase()) {
                is Resource.Error -> _inventoryUiState.value = InventoryUiState.UnAuthenticatedUser
                Resource.Loading -> _inventoryUiState.value = InventoryUiState.Loading
                is Resource.Success<UserModel?> -> _inventoryUiState.value =
                    currentUserUseCase.data?.let { userModel ->
                        getListOfTabsAsPerUser(userModel)
                    } ?: InventoryUiState.UnAuthenticatedUser
            }
        }
    }

    private fun getListOfTabsAsPerUser(userModel: UserModel): InventoryUiState {
        val tabs = mutableListOf(
            InventoryBottomNavItems.Products,
            InventoryBottomNavItems.Orders,
            InventoryBottomNavItems.Category,
            InventoryBottomNavItems.Warehouse,
        )

        if (userModel.role == UserRole.SUPER_ADMIN) {
            tabs.add(InventoryBottomNavItems.Users)
        }
        return InventoryUiState.AuthenticatedUser(userModel, tabs)
    }
}