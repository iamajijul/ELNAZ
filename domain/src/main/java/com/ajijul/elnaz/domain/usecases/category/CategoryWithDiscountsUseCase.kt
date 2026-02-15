package com.ajijul.elnaz.domain.usecases.category

import com.ajijul.elnaz.domain.model.CategoryWithDiscountsModel
import com.ajijul.elnaz.domain.model.enums.Resource
import com.ajijul.elnaz.domain.repository.category.CategoryRepository
import kotlinx.coroutines.flow.Flow

class CategoryWithDiscountsUseCase(val categoryRepository: CategoryRepository) {

    operator fun invoke(id: String): Flow<Resource<CategoryWithDiscountsModel?>> =
        categoryRepository.getCategoryWithDiscounts(categoryId = id)

}