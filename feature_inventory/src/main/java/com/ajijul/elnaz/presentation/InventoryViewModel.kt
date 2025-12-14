package com.ajijul.elnaz.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ajijul.elnaz.di.annotations.IODispatcher
import com.ajijul.elnaz.domain.model.UserModel
import com.ajijul.elnaz.domain.model.enums.Resource
import com.ajijul.elnaz.domain.model.enums.UserRole
import com.ajijul.elnaz.domain.usecases.auth.CurrentUserUseCase
import com.ajijul.elnaz.navigation.InventoryBottomNavItems
import com.ajijul.elnaz.utils.InventoryUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InventoryViewModel @Inject constructor(
    private val currentUserUseCase: CurrentUserUseCase,
    @param:IODispatcher private val ioDispatcher: CoroutineDispatcher
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