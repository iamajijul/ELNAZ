package com.ajijul.elnaz.domain.usecases.category

import com.ajijul.elnaz.domain.model.CategoryModel
import com.ajijul.elnaz.domain.model.CategoryWithDiscountsModel
import com.ajijul.elnaz.domain.repository.category.CategoryRepository
import kotlinx.coroutines.flow.Flow

class CategoryWithDiscountsUseCase(val categoryRepository: CategoryRepository) {

    operator fun invoke(id: Long): Flow<CategoryWithDiscountsModel?> =
        categoryRepository.getCategoryWithDiscounts(categoryId = id)

}