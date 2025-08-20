package com.ajijul.elnaz.domain.usecases

import com.ajijul.elnaz.domain.model.ProductModel
import com.ajijul.elnaz.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf
import java.util.Calendar

class CheckStockAlertsUseCase constructor(
    private val repository: ProductRepository
) {
    data class StockAlert(
        val lowStockProducts: List<ProductModel> = emptyList(),
        val oldStockProducts: List<ProductModel> = emptyList()
    )

    operator fun invoke(threshold: Int = 0, monthsOld: Int = 12): Flow<StockAlert> {
        val oneYearAgo = Calendar.getInstance().apply {
            add(Calendar.MONTH, -monthsOld)
        }.timeInMillis

        return flowOf(StockAlert())
    }
}