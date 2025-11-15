package com.ajijul.elnaz.domain.usecases.category

import com.ajijul.elnaz.domain.model.CategoryModel
import com.ajijul.elnaz.domain.model.CategoryWithProductsModel
import com.ajijul.elnaz.domain.repository.category.CategoryRepository
import kotlinx.coroutines.flow.Flow

class GetCategoryWithProductsUseCase(val categoryRepository: CategoryRepository) {

    operator fun invoke(categoryId: Long): Flow<CategoryWithProductsModel?> =
        categoryRepository.getCategoryWithProducts(categoryId)

}