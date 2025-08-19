package com.ajijul.elnaz.domain.usecases

import com.ajijul.elnaz.domain.model.ProductModel
import com.ajijul.elnaz.domain.repository.ItemRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import java.util.Calendar

class CheckStockAlertsUseCase constructor(
    private val repository: ItemRepository
) {
    data class StockAlert(
        val lowStockProducts: List<ProductModel> = emptyList(),
        val oldStockProducts: List<ProductModel> = emptyList()
    )

    operator fun invoke(threshold: Int = 0, monthsOld: Int = 12): Flow<StockAlert> {
        val oneYearAgo = Calendar.getInstance().apply {
            add(Calendar.MONTH, -monthsOld)
        }.timeInMillis

        return combine(
            repository.getLowStockItems(threshold),
            repository.getOldStockItems(oneYearAgo)
        ) { lowStock, oldStock ->
            StockAlert(lowStock, oldStock)
        }
    }
}