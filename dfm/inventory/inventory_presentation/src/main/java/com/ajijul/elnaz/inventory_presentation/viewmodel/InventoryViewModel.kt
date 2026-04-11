package com.ajijul.elnaz.inventory_presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ajijul.elnaz.di.entrypoints.InventoryDependenciesEntryPoint
import com.ajijul.elnaz.domain.model.UserModel
import com.ajijul.elnaz.domain.model.enums.Resource
import com.ajijul.elnaz.domain.usecases.auth.CurrentUserUseCase
import com.ajijul.elnaz.features_manager.DynamicFeatureInstaller
import com.ajijul.elnaz.features_manager.routes.InventorySubNavHostRoutes
import com.ajijul.elnaz.features_manager.routes.MainNavGraphRoutes
import com.ajijul.elnaz.inventory_presentation.utils.InventoryBottomNavItems
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class InventoryViewModel(
    val inventoryDependencies: InventoryDependenciesEntryPoint
) : ViewModel() {
    private val _inventoryUiState = MutableStateFlow<InventoryUiState>(InventoryUiState.Loading)
    val inventoryUiState: StateFlow<InventoryUiState> = _inventoryUiState

    init {
        viewModelScope.launch(inventoryDependencies.getIoDispatcher()) {
            when (val currentUserUseCase: Resource<UserModel?> = inventoryDependencies.getCurrentUserUseCase().invoke()) {
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
        val tabs = arrayListOf<InventoryBottomNavItems>()
        val dfmInstaller = inventoryDependencies.getDFMInstaller()

        if (userModel.canSeeProductsTab) {
            tabs.add(InventoryBottomNavItems.Products)
            dfmInstaller.prefetchModule(InventorySubNavHostRoutes.PRODUCTS.moduleName)
        }

        if (userModel.canSeeOrdersTab) {
            tabs.add(InventoryBottomNavItems.Orders)
            dfmInstaller.prefetchModule(InventorySubNavHostRoutes.ORDERS.moduleName)
        }

        if (userModel.canSeeCategoriesTab) {
            tabs.add(InventoryBottomNavItems.Category)
            dfmInstaller.prefetchModule(InventorySubNavHostRoutes.CATEGORY.moduleName)
        }

        if (userModel.canSeeWarehousesTab) {
            tabs.add(InventoryBottomNavItems.Warehouse)
            dfmInstaller.prefetchModule(InventorySubNavHostRoutes.WAREHOUSE.moduleName)
        }

        if (userModel.canManageUsers) {
            tabs.add(InventoryBottomNavItems.Users)
            dfmInstaller.prefetchModule(InventorySubNavHostRoutes.USERS.moduleName)
        }

        return InventoryUiState.AuthenticatedUser(userModel, tabs)
    }
}