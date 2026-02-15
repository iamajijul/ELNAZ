package com.ajijul.elnaz.domain.usecases.category

import com.ajijul.elnaz.domain.model.enums.Resource
import com.ajijul.elnaz.domain.repository.category.CategoryRepository

class UnlinkWarehouseFromCategoryUseCase(val categoryRepository: CategoryRepository) {

    suspend operator fun invoke(categoryId: String, warehouseId: String): Resource<Int> =
        categoryRepository.unlinkCategoryFromWarehouse(categoryId, warehouseId)

}