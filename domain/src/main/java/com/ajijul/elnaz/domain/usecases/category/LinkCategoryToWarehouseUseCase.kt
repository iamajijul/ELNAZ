package com.ajijul.elnaz.domain.usecases.category

import com.ajijul.elnaz.domain.model.CategoryModel
import com.ajijul.elnaz.domain.model.enums.Resource
import com.ajijul.elnaz.domain.repository.category.CategoryRepository
import kotlinx.coroutines.flow.Flow

class LinkCategoryToWarehouseUseCase(val categoryRepository: CategoryRepository) {

    suspend operator fun invoke(categoryId: String, warehouseId: String): Resource<Long> =
        categoryRepository.linkCategoryToWarehouse(categoryId, warehouseId)

}