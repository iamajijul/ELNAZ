package com.ajijul.elnaz.inventory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ajijul.elnaz.domain.repository.ItemRepository
import com.ajijul.elnaz.domain.usecases.CheckStockAlertsUseCase
import com.ajijul.elnaz.domain.usecases.GetItemsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InventoryViewModel @Inject constructor(
    private val getItemsUseCase: GetItemsUseCase,
    private val repository: ItemRepository,
    private val checkStockAlertsUseCase: CheckStockAlertsUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(InventoryState())
    val state: StateFlow<InventoryState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            combine(
                getItemsUseCase(),
                repository.getInventoryStats(),
                checkStockAlertsUseCase()
            ) { items, stats, alerts ->
                InventoryState(
                    items = items,
                    itemCount = stats.first,
                    totalValue = stats.second,
                    lowStockItems = alerts.lowStockItems,
                    oldStockItems = alerts.oldStockItems
                )
            }.collect { newState ->
                _state.value = newState
            }
        }
    }
}