package com.ajijul.elnaz.domain.usecases.category

import com.ajijul.elnaz.domain.model.CategoryModel
import com.ajijul.elnaz.domain.model.CategoryWithDiscountsModel
import com.ajijul.elnaz.domain.model.CategoryWithWarehouseModel
import com.ajijul.elnaz.domain.model.enums.Resource
import com.ajijul.elnaz.domain.repository.category.CategoryRepository
import kotlinx.coroutines.flow.Flow

class CategoryWithWarehousesUseCase(val categoryRepository: CategoryRepository) {

    operator fun invoke(id: String): Flow<Resource<CategoryWithWarehouseModel?>> =
        categoryRepository.getCategoryWithWarehouses(categoryId = id)

}