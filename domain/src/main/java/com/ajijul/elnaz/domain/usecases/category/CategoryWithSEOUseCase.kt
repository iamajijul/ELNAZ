package com.ajijul.elnaz.domain.usecases.category

import com.ajijul.elnaz.domain.model.CategoryModel
import com.ajijul.elnaz.domain.model.CategoryWithDiscountsModel
import com.ajijul.elnaz.domain.model.CategoryWithSEOModel
import com.ajijul.elnaz.domain.model.enums.Resource
import com.ajijul.elnaz.domain.repository.category.CategoryRepository
import kotlinx.coroutines.flow.Flow

class CategoryWithSEOUseCase(val categoryRepository: CategoryRepository) {

    suspend operator fun invoke(id: String): Flow<Resource<CategoryWithSEOModel?>> =
        categoryRepository.getCategoryWithSEO(categoryId = id)

}