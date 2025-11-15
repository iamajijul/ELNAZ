package com.ajijul.elnaz.domain.usecases.category

import com.ajijul.elnaz.domain.model.CategoryModel
import com.ajijul.elnaz.domain.repository.category.CategoryRepository
import kotlinx.coroutines.flow.Flow

class GetCategoryById(val categoryRepository: CategoryRepository) {

    suspend operator fun invoke(categoryId: Long): CategoryModel? =
        categoryRepository.getCategoryById(categoryId)

}