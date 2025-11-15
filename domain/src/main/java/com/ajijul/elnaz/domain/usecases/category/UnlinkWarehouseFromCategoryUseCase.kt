package com.ajijul.elnaz.domain.usecases.category

import com.ajijul.elnaz.domain.repository.category.CategoryRepository

class UnlinkWarehouseFromCategoryUseCase(val categoryRepository: CategoryRepository) {

    suspend operator fun invoke(categoryId: Long, warehouseId: Long): Unit =
        categoryRepository.unlinkCategoryFromWarehouse(categoryId, warehouseId)

}