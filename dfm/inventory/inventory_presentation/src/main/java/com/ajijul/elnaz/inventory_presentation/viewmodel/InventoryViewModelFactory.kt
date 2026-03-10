package com.ajijul.elnaz.inventory_presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ajijul.elnaz.di.entrypoints.InventoryDependenciesEntryPoint
import com.ajijul.elnaz.domain.usecases.auth.CurrentUserUseCase
import kotlinx.coroutines.CoroutineDispatcher

class InventoryViewModelFactory(
    var inventoryDependenciesEntryPoint: InventoryDependenciesEntryPoint
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return InventoryViewModel(
            inventoryDependenciesEntryPoint.getCurrentUserUseCase(),
            inventoryDependenciesEntryPoint.getIoDispatcher()
        ) as T
    }
}