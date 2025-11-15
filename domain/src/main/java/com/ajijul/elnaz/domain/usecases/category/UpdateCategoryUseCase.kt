package com.ajijul.elnaz.domain.usecases.category

import com.ajijul.elnaz.domain.model.CategoryModel
import com.ajijul.elnaz.domain.repository.category.CategoryRepository
import kotlinx.coroutines.flow.Flow

class UpdateCategoryUseCase(val categoryRepository: CategoryRepository) {

    suspend operator fun invoke(categoryModel: CategoryModel): Unit =
        categoryRepository.updateCategory(categoryModel)

}