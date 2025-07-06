package com.ajijul.elnaz.domain.usecases

import com.ajijul.elnaz.data.repository.ItemRepository
import com.ajijul.elnaz.domain.model.Item
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import java.util.Calendar
import javax.inject.Inject

class CheckStockAlertsUseCase @Inject constructor(
    private val repository: ItemRepository
) {
    data class StockAlert(
        val lowStockItems: List<Item> = emptyList(),
        val oldStockItems: List<Item> = emptyList()
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