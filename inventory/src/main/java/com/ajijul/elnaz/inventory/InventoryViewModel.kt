package com.ajijul.elnaz.inventory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ajijul.elnaz.domain.repository.ProductRepository
import com.ajijul.elnaz.domain.usecases.CheckStockAlertsUseCase
import com.ajijul.elnaz.domain.usecases.GetProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InventoryViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    private val repository: ProductRepository,
    private val checkStockAlertsUseCase: CheckStockAlertsUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(InventoryState())
    val state: StateFlow<InventoryState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            combine(
                getProductsUseCase(),
                repository.getInventoryStats(),
                checkStockAlertsUseCase()
            ) { items, stats, alerts ->
                InventoryState(
                    products = items,
                    itemCount = stats.first,
                    totalValue = stats.second,
                    lowStockProducts = alerts.lowStockProducts,
                    oldStockProducts = alerts.oldStockProducts
                )
            }.collect { newState ->
                _state.value = newState
            }
        }
    }
}